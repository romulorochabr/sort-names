package com.megaport.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Romulo Rocha
 *
 * This is a console application that receives a parameter with a path of a file containing 
 * a list of names in an specific format (SURNAME, NAME) and produces another file 
 * with a sorted list of names keeping the same format. 
 * <p>
 * Is is mandatory that pass at the execution a valid path and filename. 
 * There are several validations to guarantee the final result and prevent the application to face an error. 
 * 
 * @param - args - valid path + filename. For example: c:/users/files/names.txt
 * 
 */
@SpringBootApplication
public class ParserApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ParserApplication.class, args);
	}

}
