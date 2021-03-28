package com.megaport.parser.service;

import java.util.List;


/**
 * @author Romulo Rocha
 *
 * Produces the list of <T> based on the sourceFile
 *
 * @param <T> - Entity representing the operation
 */
public interface FileReader<T> {
	
	
	/**
	 * Returns the list of <T> according to the sourceFile
	 * @param sourceFile
	 * @return
	 * @throws Exception 
	 */
	List<T> readFile(String sourceFile) throws Exception;

}
