package com.megaport.parser.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class FileHelperImpl implements FileHelper {

	@Override
	public List<String> getLinesFromFile(String sourceFilename) throws IOException {
		Path filePath = Paths.get(sourceFilename);
		return Files.lines(filePath).collect(Collectors.toList());
	}

}
