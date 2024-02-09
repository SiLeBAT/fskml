package de.bund.bfr.fskml;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * 
 * abstract class that defines how a script is parsed in order to obtain the libraries, source files and the script proper.
 * 
 * 
 * @author Thomas Schüler
 *
 */
public abstract class Script {
  String script;
  final List<String> libraries = new LinkedList<>();
  final List<String> sources = new LinkedList<>();
  private final Map<String, String> packageMap = new HashMap<>();
 

 
  /**
   * Gets the script.
   * 
   * @return the script.
   */
  public String getScript() {
      return script;
  }

  /**
   * Gets the names of the source files linked in the script.
   * 
   * @return the names of the source files linked in the script.
   */
  public List<String> getSources() {
      return sources;
  }

  /**
   * Gets the names of the libraries imported in the script.
   * 
   * @return the names of the libraries imported in the script.
   */
  public List<String> getLibraries() {
      return libraries;
  }
  
  /**
   * Gets the packageMap.
   * 
   * @return the names and version of the libraries imported in the script.
   */
  public Map<String, String> getPackageMap() {
	   return packageMap;
  }
}
