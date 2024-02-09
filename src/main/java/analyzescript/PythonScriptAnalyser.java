package analyzescript;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PythonScriptAnalyser {
	
	/**
     * Extracts the Python version given the path to the Python binary.
     * @param pythonBinPath The path to the Python binary.
     * @return The Python version as a String.
     */
    public static String extractPythonVersion(String pythonBinPath) {
        // Command to retrieve Python version
        String command = pythonBinPath+ " --version";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();

            // Python writes version info to stderr, but some newer versions might write to stdout.
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = reader.readLine();
            if (line == null || line.isEmpty()) {
                // If nothing was captured from stderr, try stdout.
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                line = reader.readLine();
            }

            int exitCode = process.waitFor();
            if (exitCode == 0 && line != null && !line.isEmpty()) {
                return line; // Return the version string
            } else {
                return "Failed to extract Python version. Process exited with code " + exitCode;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error extracting Python version: " + e.getMessage();
        }
    }
    
    /**
     * Executes a Python script using a specified Python executable and additional arguments.
     *
     * @param pythonPath       Path to the Python executable.
     * @param scriptArguments  Arguments to be passed to the Python script, including the script to analyze and the environment path.
     * @return The output of the executed script.
     */
    public static String executePythonScript(String pythonPath, String... scriptArguments) {
    	
    	Path tempScript = null;
        try {
        	tempScript = Paths.get(System.getProperty("user.home"), "setup_dependencies.py");
            if(!tempScript.toFile().exists())
            	tempScript = Files.createFile(tempScript);
            
            // Use try-with-resources to ensure that the InputStream and BufferedReader are closed after use
            try (InputStream in = PythonScriptAnalyser.class.getResourceAsStream("/setup_dependencies.py");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                

                // Copy the content of the resource to the temporary file
                Files.copy(in, tempScript, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Resource copied to temporary file: " + tempScript.toAbsolutePath());
            } catch (IOException e) {
                System.out.println("Failed to copy the resource");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Failed to create a temporary file");
            e.printStackTrace();
        }
        String setupScriptPath = tempScript.toAbsolutePath().toString(); 
    	
    	ProcessBuilder pb = new ProcessBuilder(pythonPath, setupScriptPath);
        try {
            Process p = pb.start();

            // Capture and print the standard output
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // Capture and print the standard error
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            int exitCode = p.waitFor();
            if (exitCode == 0) {
                System.out.println("Dependency setup complete.");
            } else {
                System.out.println("Dependency setup failed with exit code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    	
    	// Prepare the command with Python executable, script path, and script arguments
        List<String> command = new ArrayList<>();
        command.add(pythonPath);
        Path tempFile = null;
        try {
            tempFile = Paths.get(System.getProperty("user.home"), "analyze_script.py");
            if(!tempFile.toFile().exists())
            	tempFile = Files.createFile(tempFile);
            
            // Use try-with-resources to ensure that the InputStream and BufferedReader are closed after use
            try (InputStream in = PythonScriptAnalyser.class.getResourceAsStream("/analyze_script.py");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                

                // Copy the content of the resource to the temporary file
                Files.copy(in, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Resource copied to temporary file: " + tempFile.toAbsolutePath());
                command.add(tempFile.toAbsolutePath().toString());
            } catch (IOException e) {
                System.out.println("Failed to copy the resource");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Failed to create a temporary file");
            e.printStackTrace();
        }
        
        for (String arg : scriptArguments) {
            command.add(arg);
        }

        // Use ProcessBuilder to execute the command
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true); // Redirect error stream to the output stream
        StringBuilder output = new StringBuilder();

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
            int exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null; // Or handle the error as appropriate for your application
        }

        return output.toString();
    }

    
    public static Map<String, String> parseScriptOutput(String scriptOutput, Map<String, String> packageMap) {
    	System.out.println(scriptOutput);
        Pattern pattern = Pattern.compile("\\('([^']*)', '([^']*)'\\)");
        Matcher matcher = pattern.matcher(scriptOutput);

        while (matcher.find()) {
            String packageName = matcher.group(1);
            String packageVersion = matcher.group(2);
            packageMap.put(packageName, packageVersion);
        }

        return packageMap;
    }

    public static void main(String[] args) {
        // Example usage
        String pythonBinPath = "/Users/ahmadswaid/opt/anaconda3/envs/py10/bin"; // Update this path to the Python binary
        String pythonVersion = extractPythonVersion(pythonBinPath);
        System.out.println(pythonVersion);
    }
}
