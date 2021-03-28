package com.megaport.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;

import com.megaport.parser.util.FileHelper;

@SpringBootTest
@Profile("test")
public class ParserApplicationIntegrationTests {

	@Autowired
    ApplicationContext context;
	
	@Autowired
	private FileHelper fileHelper;
	
	@Test
    public void execCommandLineRunner() {
		try {
			final String filename = "names";
			final String fileExtension = "txt";
			
			ClassLoader classLoader = getClass().getClassLoader();
			
			// Remove the sorted file to rerun the test
			File fileSorted = new File(classLoader.getResource(filename + "-sorted." + fileExtension).getFile());
			Path path = Paths.get(fileSorted.getPath());
			Files.deleteIfExists(path);
  		
	        assertEquals(fileSorted.exists(), false);
	        
	        final String sortedFile = filename + "." + fileExtension;
			File file = new File(classLoader.getResource(sortedFile).getFile());
			
			Runner runner= context.getBean(Runner.class);
	        runner.run(new String[]{file.toString()});
	        
	        List<String> lines = fileHelper.getLinesFromFile(fileSorted.getAbsolutePath());
	        
	        assertEquals(fileSorted.exists(), true);
	        assertNotNull(lines);
	        assertEquals(lines.size(), 18);
	        
		} catch (Exception ex) {
			fail("Error running the line runner");
		}
    }
}
