package com.megaport.parser.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidatorImplTest {

	private String fileName;
	
	@Autowired
	EventValidator validator;
	
	
	@Test
	void testInvalidFileNameNull() {
	
		this.fileName = null;
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			validator.validate(this.fileName);
	    });

	    String expectedMessage = "Source filename is missing. Please, supply a source filename with proper extension.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	    
	}
	
	@Test
	void testInvalidFileNameBlank() {
	
		this.fileName = " ";
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			validator.validate(this.fileName);
	    });

	    String expectedMessage = "Source filename is missing. Please, supply a source filename with proper extension.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	    
	}
	
	@Test
	void testInvalidFileNameNotFound() {
		
		this.fileName = "c:\\users\\files\\";
		
		Exception exception = assertThrows(Exception.class, () -> {
			validator.validate(this.fileName);
	    });

	    String expectedMessage = "Filename not found in the arguments. Please, supply a source filename with proper extension.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testInvalidFileNameSourceInvalid() {
	
		this.fileName = "c:\\users\\files\\list";
		
		Exception exception = assertThrows(Exception.class, () -> {
			validator.validate(this.fileName);
	    });

	    String expectedMessage = "Source filename invalid. Please, ensure the source filename has an extension. For example: filename.txt";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testValidFilenameShortVersion() throws Exception {
		
		this.fileName = "list.txt";
		
	    assertDoesNotThrow(() -> {
			validator.validate(this.fileName);
	    });

	}
	
	@Test
	void testValidFileType() throws Exception {
		
		this.fileName = "c:\\users\\files\\list.txt";
		
	    assertDoesNotThrow(() -> {
			validator.validate(this.fileName);
	    });

	}
	
	@Test
	void testValidFileTypeNotValid() throws Exception {
		
		this.fileName = "c:\\users\\files\\list.zip";
		
	    // Exception(String.format("'%s' is not" + " a plain text file.%n", this.filename)); 
	    
	    Exception exception = assertThrows(Exception.class, () -> {
			validator.validate(this.fileName);
	    });

	    String expectedMessage =  "c:\\users\\files\\list.zip is not a plain text file.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));

	}
	
}
