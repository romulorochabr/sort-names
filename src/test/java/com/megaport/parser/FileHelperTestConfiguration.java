package com.megaport.parser;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.megaport.parser.util.FileHelper;

@Profile("test")
@Configuration
public class FileHelperTestConfiguration {

	@Bean
    @Primary
    public FileHelper fileHelperService() {
        return Mockito.mock(FileHelper.class);
    }
}
