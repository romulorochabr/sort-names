package com.megaport.parser.util;

public final class Constants {

	private Constants () {}
	
	public static final String REGEX_FILE_LINE_STRUCTURE = "([A-Za-z])+,([\\s+]?[A-Za-z]+)";
	public static final String REGEX_FILE_NAME = "[^\\\\/:*?\"<>|\\r\\n]+$";
	public static final String REGEX_VALIDATE_FILENAME_EXTENSION = "^[\\w,\\s-]+\\.[A-Za-z]{3}$";
	
	// Mime types
	public static final String TEXT_PLAIN = "text/plain";
	public static final String APPLICATION_ZIP = "application/zip";
}
