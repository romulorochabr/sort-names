package com.megaport.parser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.megaport.parser.model.Person;
import com.megaport.parser.service.FileReader;
import com.megaport.parser.service.FileWriter;
import com.megaport.parser.util.EventValidator;

@Component
public class Runner implements CommandLineRunner {
	
	private static Logger LOG = LoggerFactory
		      .getLogger(Runner.class);
	
	@Autowired
	private EventValidator eventValidator;
	
	@Autowired
	private FileReader<Person> personFileReader;
	
	@Autowired 
	private FileWriter<Person> personFilerWriter;

	@Override
	public void run(String... args) {
		LOG.info("EXECUTING : Sorting routine.");

		try {
			
			if(args == null || args.length == 0)
				throw new IllegalArgumentException("Please, provide a text file containing the list of surnames and names");
			final String sourceFilename = args[0];
			
			// Validate the arguments, sourcefile name
			eventValidator.validate(sourceFilename);
			
			// Read the file and create a list of Persons
			List<Person> filteredLines = personFileReader.readFile(sourceFilename);

			// Write a new file with a sorted list of names, by SURNAME, NAME
			personFilerWriter.writeFile(filteredLines, sourceFilename);

		} catch (Exception ex) { // Main point to catch errors and log messages
			LOG.error(ex.getMessage());
			LOG.error("Finished with an error. Please, try again");
		}
	}
	
}
