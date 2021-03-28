package com.megaport.parser.util;

import java.io.IOException;
import java.util.List;

public interface FileHelper {

	List<String> getLinesFromFile(String sourceFilename) throws IOException;
}
