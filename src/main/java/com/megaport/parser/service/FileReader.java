package com.megaport.parser.service;

import java.util.List;

public interface FileReader<T> {
	
	List<T> readFile(String sourceFile) throws Exception;

}
