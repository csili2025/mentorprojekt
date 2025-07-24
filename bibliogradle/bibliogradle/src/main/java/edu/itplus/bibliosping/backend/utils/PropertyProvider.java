package edu.itplus.bibliosping.backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.PropertyResolver;

@Configuration
@PropertySource("classpath:/Bibiospring.properties")
public class PropertyProvider {
    @Autowired
    private PropertyResolver propertyResolver;

    public String getProperty(String key) {
        return propertyResolver.getProperty(key);
    }
}
