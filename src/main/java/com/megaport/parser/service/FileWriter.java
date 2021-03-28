package com.megaport.parser.service;

import java.util.List;

public interface FileWriter<T> {

	void writeFile (List<T> persons, String sourceFile) throws Exception;
	
}
