import ast
import subprocess
import sys
import pkg_resources
import logging
import requests
import re
import os
from pathlib import Path
import argparse
from stdlib_list import stdlib_list

def get_stdlib_modules_for_version(version):
    """Return a list of standard library modules for a given Python version."""
    return stdlib_list(version)

# Example usage
python_version = '3.8'  # Specify the Python version you're interested in
stdlib_modules = get_stdlib_modules_for_version(python_version)

def is_builtin_module(module_name):
    return module_name in sys.builtin_module_names or module_name in stdlib_modules


def get_package_name(package_name):
    response = requests.get(f'https://pypi.org/pypi/{package_name}/json')
    if response.status_code == 200:
        data = response.json()
        # Extracting the package name
        package_name = data['info']['name']
        return package_name
    else:
        return None


def get_recommended_package_name(package_name):
    response = requests.get(f'https://pypi.org/pypi/{package_name}/json')
    if response.status_code == 200:
        data = response.json()
        # Extracting the package name
        description = data.get('info', {}).get('description', '')
        match = re.search(r"use `([a-zA-Z0-9\-_]+)` instead", description)
        if match:
            return match.group(1)
    return None


def find_imports(script_path, env_path):
    if env_path:
        try:
            # Get the directory of analyze_script.py
            script_dir = os.path.dirname(os.path.realpath(__file__))

            # Construct the path to the Python executable in the specified environment
            python_executable = f"{env_path}/bin/python"

            # Set PYTHONPATH to include the script directory
            env = os.environ.copy()
            if 'PYTHONPATH' in env:
                env['PYTHONPATH'] = f"{script_dir}:{env['PYTHONPATH']}"
            else:
                env['PYTHONPATH'] = script_dir

            # Construct the command to run the script in the specified environment
            command = [python_executable, '-c', f'import analyze_script; analyze_script.find_imports_inner("{script_path}")']

            # Run the command with the updated environment
            result = subprocess.run(command, capture_output=True, text=True, env=env)
            print(result)
            return result.stdout
        except Exception as e:
            print(e)
            print("Failed to run the script in the specified environment.")
            sys.exit(1)


def find_imports_inner(script_path):
    with open(script_path, 'r') as file:
        script_content = file.read()

    tree = ast.parse(script_content)
    imports = []
    for node in ast.walk(tree):
        package_version = None
        if isinstance(node, ast.Import):
            for name in node.names:
                package_name = name.name.split('.')[0]
                  # Initialize package version to None
                if is_builtin_module(package_name):
                    continue
                # Check if the package is installed and get its version
                try:
                    distribution = pkg_resources.get_distribution(package_name)
                    package_version = distribution.version
                except pkg_resources.DistributionNotFound:
                    imports.append((package_name,""))
                    continue
                
                if package_version == None:
                    package_name = get_package_name(package_name)
                    distribution = pkg_resources.get_distribution(package_name)
                    package_version = distribution.version
                imports.append((package_name, package_version))
        elif isinstance(node, ast.ImportFrom):
            module = node.module.split('.')[0]
            if is_builtin_module(module):
                continue
            # Check if the package is installed and get its version
            try:
                distribution = pkg_resources.get_distribution(module)
                package_version = distribution.version
            except pkg_resources.DistributionNotFound:
                imports.append((module,""))
                continue
            
            if package_version == None:
                    package_name = get_package_name(module)
                    distribution = pkg_resources.get_distribution(package_name)
                    package_version = distribution.version
            imports.append((module, package_version))
    print(imports)
    return imports

def create_requirements_file(script_path, env_path, output_path='requirements.txt'):
    imported_packages = find_imports(script_path, env_path)
    tuples_list = ast.literal_eval(imported_packages)
    print("tuples_list",tuples_list)

    with open(output_path, 'w') as req_file:
        for package_name, package_version in tuples_list:
            if package_version:
                req_file.write(f"{package_name}=={package_version}\n")
            else:
                req_file.write(f"{package_name}\n")

def create_virtual_environment(venv_path='venv'):
    # Configure logging
    logging.basicConfig(level=logging.INFO)

    # Start the subprocess to create a virtual environment
    with subprocess.Popen([sys.executable, '-m', 'venv', venv_path], 
                          stdout=subprocess.PIPE, 
                          stderr=subprocess.PIPE, 
                          text=True, 
                          bufsize=1, 
                          universal_newlines=True) as process:

        # Read and log stdout line by line
        for line in process.stdout:
            logging.info(line.strip())

        # Check if there are errors and log them
        if process.stderr:
            for line in process.stderr:
                logging.error(line.strip())

        # Wait for the subprocess to finish
        process.wait()


def install_packages_in_virtual_env(venv_path='venv', requirements_file='requirements.txt'):
    # Configure logging
    logging.basicConfig(level=logging.INFO)

    # Construct the path to pip within the virtual environment
    pip_path = f'{venv_path}/bin/pip' if sys.platform != 'win32' else f'{venv_path}\\Scripts\\pip.exe'

    # Start the subprocess and open streams for stdout and stderr
    with subprocess.Popen([pip_path, 'install', '-r', requirements_file], 
                          stdout=subprocess.PIPE, 
                          stderr=subprocess.PIPE, 
                          text=True, 
                          bufsize=1, 
                          universal_newlines=True) as process:

        # Read and log stdout line by line
        for line in process.stdout:
            logging.info(line.strip())

        # Check if there are errors and log them
        if process.stderr:
            for line in process.stderr:
                logging.error(line.strip())

        # Wait for the subprocess to finish
        process.wait()



def find_python_executables(start_path="/"):
    for root, dirs, files in os.walk(start_path):
        for file in files:
            if file.startswith("python"):
                yield os.path.join(root, file)

def find_conda_environments():
    result = subprocess.run(['conda', 'env', 'list'], stdout=subprocess.PIPE, text=True)
    for line in result.stdout.splitlines():
        if line and not line.startswith('#'):
            parts = line.split()
            # The environment name is always the first part
            env_name = parts[0]
            # The environment path is always the last part
            env_path = parts[-1]
            yield env_name, env_path



def main(script_path, env_path):
    
# Example usage - This might take a long time depending on the size of your filesystem
    
    
    create_requirements_file(script_path,env_path)
    #create_virtual_environment()
    #install_packages_in_virtual_env()

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Analyze Python script and generate requirements.txt')
    parser.add_argument('script_path', type=str, help='Path to the Python script to analyze')
    parser.add_argument('env_path', type=str, help='Path to the conda environment')

    args = parser.parse_args()

    main(args.script_path, args.env_path)