/*******************************************************************************
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
 *******************************************************************************/
package de.bund.bfr.fskml;

import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * @author Miguel Alba
 */
public class RScriptTest extends TestCase {

	public void testScript() throws IOException {
		
		// Creates temporary file. Fails the test if an error occurs.
		File f = File.createTempFile("temp", "");
		f.deleteOnExit();

		String origScript = "# This is a comment line: It should not appear in the simplified version\n"
				+ "library(triangle)\n" + "source(other.R)\n"
				+ "hist(result, breaks=50, main=\"PREVALENCE OF PARENT FLOCKS\")\n";
		FileUtils.writeStringToFile(f, origScript, "UTF-8");
		RScript rScript = new RScript(f);

		assertEquals(origScript, rScript.getScript());
		assertEquals(Collections.singletonList("triangle"), rScript.getLibraries());
		assertEquals(Collections.singletonList("other.R"), rScript.getSources());
	}
}