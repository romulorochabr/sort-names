package com.megaport.parser.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.megaport.parser.model.Person;

@SpringBootTest
public class PersonFileWriterImplTest {

	private String fileName;
	private List<Person> persons;
	
	@Autowired
	FileWriter<Person> fileWriter;
	
	@Test
	void testInvalidFileNameNull() {
	
		this.fileName = null;
		this.persons = this.getListWithData();
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			fileWriter.writeFile(this.persons, this.fileName);
	    });

	    String expectedMessage = "The source Filename cannot be null or empty.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testInvalidFileNameBlank() {
	
		this.fileName = " ";
		this.persons = this.getListWithData();
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			fileWriter.writeFile(this.persons, this.fileName);
	    });

	    String expectedMessage = "The source Filename cannot be null or empty.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testInvalidListPersonNull() {
	
		this.fileName = "file.txt";
		this.persons = null;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			fileWriter.writeFile(this.persons, this.fileName);
	    });

	    String expectedMessage = "The list of persons cannot be null or empty.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testInvalidListPersonEmpty() {
	
		this.fileName = "file.txt";
		this.persons = new ArrayList<Person>();
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			fileWriter.writeFile(this.persons, this.fileName);
	    });

	    String expectedMessage = "The list of persons cannot be null or empty.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	private List<Person> getListWithData(){
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Romulo", "Rocha"));
		persons.add(new Person("Afonso", "Rocha"));
		return persons;
	}
}
