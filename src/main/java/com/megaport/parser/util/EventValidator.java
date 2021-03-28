package com.megaport.parser.util;

/**
 * @author Romulo Rocha
 *
 * Validates the event based on the filename 
 */
public interface EventValidator {

	/**
	 * Runs several validations against the source file
	 * 
	 * @param filename - valid path and filename
	 * @throws Exception
	 */
	void validate(String filename) throws Exception;
}
