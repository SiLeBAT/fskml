/*
 ***************************************************************************************************
 * Copyright (c) 2017 Federal Institute for Risk Assessment (BfR), Germany
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors: Department Biological Safety - BfR
 *************************************************************************************************
 */

package de.bund.bfr.fskml;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;

/**
 * simple factory to create a {@Script} instance depending on the language of the file. 
 * @author Thomas Schueler
 *
 */
public class ScriptFactory {

	/**
	 * 
	 * @param file A file containing script code (e.g.: R code)
	 * @return instance of Script
	 * @throws IOException
	 */
	public static Script createScript(final File file)throws IOException {
		Script script = null;
		
		String language = FilenameUtils.getExtension(file.getPath());
		if(language.toLowerCase().startsWith("r"))
		{
			script = new RScript(file);
		}
		if(language.toLowerCase().startsWith("py"))
		{
			script = new PythonScript(file);
		}
		
		return script;
	}
	/**
	 * 
	 * @param String script code (e.g.: R code)
	 * @return instance of Script
	 * @throws IOException
	 */
	public static Script createScript(String scriptString, String language, String pythonPath, String analyzedScriptPath, String envpath)throws IOException {
		Script script = null;
		
		if(language.toLowerCase().startsWith("r"))
		{
			script = new RScript(scriptString);
		}
		if(language.toLowerCase().startsWith("py"))
		{
			script = new PythonScript(scriptString,  pythonPath,  analyzedScriptPath,  envpath);
		}
		
		return script;
	}
}
