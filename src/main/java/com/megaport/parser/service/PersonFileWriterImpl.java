package com.megaport.parser.service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.megaport.parser.model.Person;
import com.megaport.parser.util.Constants;

/**
 * @author Romulo Rocha
 *
 * Writes into a text file (UTF8) the lines from a list of persons.
 * The final text file name is based on the source file name plus -sorted.
 * For example: if source file is names.txt the final file is names-sorted.txt.
 * 
 */
@Component
public class PersonFileWriterImpl implements FileWriter<Person>{

	private static Logger LOG = LoggerFactory
		      .getLogger(PersonFileWriterImpl.class);
	
	/**
	 * Writes the list of persons to a file.
	 * Valid source file name with an extension should be provided.
	 * Method transforms the source file name to the destiny file name. Refer to an example in the class javadocs.
	 * 
	 * @param sourceFile - valid path and filename
	 * @return List<Person>
	 * @throws Exception
	 */
	public void writeFile (final List<Person> persons, final String sourceFileName) throws Exception {
		
		if(persons == null || persons.size() == 0)
			throw new IllegalArgumentException("The list of persons cannot be null or empty.");
		if(sourceFileName == null || sourceFileName.isBlank())
			throw new IllegalArgumentException("The source Filename cannot be null or empty.");
		
		Pattern p = Pattern.compile(Constants.REGEX_FILE_NAME); // Retrieves the filename  from the path
		Matcher m = p.matcher(sourceFileName);
		if(m.find()) {
			
			String destinationFile = m.group(0);
			String [] pathParts = sourceFileName.split(destinationFile);
			String [] filesParts = destinationFile.split("\\.");
			destinationFile = new StringBuilder()
					.append(pathParts[0])
					.append(filesParts[0])
					.append("-sorted")
					.append(".")
					.append(filesParts[1])
					.toString();
				
			Files.write(Path.of(destinationFile),
					(Iterable<String>) persons.stream().map(s -> {
							LOG.info(s.toString());
							return String.valueOf(s);
						}).collect(Collectors.toList()),
					StandardCharsets.UTF_8);
			
			LOG.info("Finished: created " + destinationFile);
		} else {
			throw new Exception("Error trying to match source file name");
		}
	}
}
