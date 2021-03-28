package com.megaport.parser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.megaport.parser.model.Person;
import com.megaport.parser.util.FileHelper;

@ActiveProfiles("test")
@SpringBootTest
public class PersonFileReaderImplTest {

	private String filename;
	
	@Autowired
	FileReader<Person> fileReader;
	
	@MockBean
	FileHelper fileHelper;
	
	@Test
	void testInvalidFileNameNull() {
	
		this.filename = null;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			fileReader.readFile(this.filename);
	    });

	    String expectedMessage = "Source File Name cannot be null or empty.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testInvalidFileNameBlank() {
	
		this.filename = " ";
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			fileReader.readFile(this.filename);
	    });

	    String expectedMessage = "Source File Name cannot be null or empty.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testReturnListOfPersons() {
		this.filename = "filename.txt";
		
		try {
			when(fileHelper.getLinesFromFile(this.filename)).thenReturn(getListOfStrings());
		
			List<Person> persons = fileReader.readFile(this.filename);
		
			assertNotNull(persons);
			assertEquals(persons.size(), 4);
			
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	private List<String> getListOfStrings(){
		List<String> list = new ArrayList<String>();
		list.add("ROCHA, ROMULO");
		list.add("ROCHA, Rosario");
		list.add("rocha, romulo");
		list.add("  ");
		list.add("ROCHA,ROMULO");
		list.add("ROCHAROO");
		list.add(null);
		list.add("SMITH, Ronald");
		list.add("SMITH, FRED");
		
		return list;
	}
	
}
