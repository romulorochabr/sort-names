package com.megaport.parser.service;

import java.util.List;

/**
 * @author Romulo Rocha
 * 
 * Transforms a list of <T> into a text file
 *
 * @param <T> - Object representing the entity that is doing to be saved in the file
 */
public interface FileWriter<T> {


	/**
	 * @param list - List used to write the final source
	 * @param sourceFile - String representing a valid path and filename.
	 * @throws Exception
	 */
	void writeFile (List<T> list, String sourceFile) throws Exception;
	
}
