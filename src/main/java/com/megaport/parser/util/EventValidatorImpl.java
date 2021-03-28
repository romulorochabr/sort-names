package com.megaport.parser.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventValidatorImpl implements EventValidator {

	private static Logger LOG = LoggerFactory
		      .getLogger(EventValidatorImpl.class);
	
	private String filename;

	public void validate(String filename) throws Exception {
		this.filename = filename;
		this.validateArguments();
		this.validateSouceFilename();
		this.validateFileType();
	}

	private void validateArguments() throws Exception {
		if (filename == null || filename.isBlank())
			throw new IllegalArgumentException(
					"Source filename is missing. Please, supply a source filename with proper extension.");
	}

	private void validateSouceFilename() throws Exception {

		Pattern p = Pattern.compile(Constants.REGEX_FILE_NAME); // Retrieves the filename from the path
		Matcher m = p.matcher(this.filename);
		if (m.find()) {

			String destinationFile = m.group(0);
			if (!destinationFile.matches(Constants.REGEX_VALIDATE_FILENAME_EXTENSION))
				throw new Exception(
						"Source filename invalid. Please, ensure the source filename has an extension. For example: filename.txt");
		} else {
			throw new Exception(
					"Filename not found in the arguments. Please, supply a source filename with proper extension.");
		}

	}

	private void validateFileType() throws Exception {
		try {
			Path filePath = Paths.get(this.filename);
			String type = Files.probeContentType(filePath);
			if (type == null) {
				throw new Exception(String.format("%s has an unknown filetype.%n", this.filename));
			} else if (!type.equals(Constants.TEXT_PLAIN)) { 
				throw new Exception(String.format("%s is not a plain text file.%n", this.filename));
			}
		} catch (IOException x) {
			LOG.error(x.getMessage());
		}
	}

}
