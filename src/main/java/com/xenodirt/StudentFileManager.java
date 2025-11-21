package com.xenodirt;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentFileManager {

    private final ObjectMapper objectMapper;

    public StudentFileManager() {
        this.objectMapper = new ObjectMapper();
    }

    // Save list of students to file using serialization
    public void saveToFile(File file, List<Student> students) throws JacksonException {
        objectMapper.writeValue(file, students);
    }

    // Load list of students from JSON file
    public List<Student> loadFromFile(File file) throws JacksonException {
        if (file == null) {
            return new ArrayList<>();
        }

        return Arrays.asList(objectMapper.readValue(file, Student[].class));
    }
}