package com.megaport.parser.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megaport.parser.model.Person;
import com.megaport.parser.util.Constants;
import com.megaport.parser.util.FileHelper;

/**
 * @author Romulo Rocha
 *
 * Class that reads a text file and transforms into a list of Persons
 */
@Component
public class PersonFileReaderImpl implements FileReader<Person>{

	@Autowired
	private FileHelper fileHelper;
	
	/**
	 * Returns the list of persons according to the sourceFile.
	 * Valid source file name with an extension should be provided.
	 * Method removes any null, blank or line that does not match the format SURNAME, NAME or SURNAME, NAME
	 * 
	 * @param sourceFile - valid path and filename
	 * @return List<Person>
	 * @throws Exception 
	 */
	public List<Person> readFile(final String sourceFile) throws Exception {
		if(sourceFile == null || sourceFile.isBlank())
			throw new IllegalArgumentException("Source File Name cannot be null or empty.");
		
		List<String> lines = fileHelper.getLinesFromFile(sourceFile);  

		List<Person> filteredLines = lines.stream().filter(s -> s != null && !s.isEmpty() && s.matches(Constants.REGEX_FILE_LINE_STRUCTURE))
				.map(Person::mapToPerson).distinct()
				.sorted(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)) 														// classes
				.collect(Collectors.toList());
		return filteredLines;
		
	}
}
