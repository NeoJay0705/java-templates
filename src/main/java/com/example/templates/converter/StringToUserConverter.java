package com.example.templates.converter;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.converter.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringToUserConverter implements Converter<String, User> {

    @Override
    public User convert(String source) {
        try {
            return new ObjectMapper().readValue(source, User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ConversionNotSupportedException("value", User.class, e);
        }
    }
    
}
