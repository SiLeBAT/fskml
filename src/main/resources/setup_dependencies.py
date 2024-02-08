import subprocess
import pkg_resources
import sys

def install_package(package_name):
    subprocess.check_call([sys.executable, "-m", "pip", "install", package_name])

try:
    pkg_resources.get_distribution('stdlib-list')
except pkg_resources.DistributionNotFound:
    install_package('stdlib-list')
