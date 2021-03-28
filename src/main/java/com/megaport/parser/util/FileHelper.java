package com.megaport.parser.util;

import java.io.IOException;
import java.util.List;

/**
 * @author Romulo Rocha
 * 
 * Helper to retrieve lines from source file
 *
 */
public interface FileHelper {

	/**
	 * Retrieve the files from the source file 
	 * 
	 * @param sourceFilename
	 * @return list of strings based on the file lines
	 * @throws IOException
	 */
	List<String> getLinesFromFile(String sourceFilename) throws IOException;
}
