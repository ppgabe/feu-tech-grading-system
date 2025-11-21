package com.xenodirt;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentFileManager {

    private final ObjectMapper objectMapper;
    private final File studentsFile;

    public StudentFileManager(File studentsFile) {
        this.objectMapper = new ObjectMapper();
        this.studentsFile = studentsFile;
    }

    // Save list of students to file using serialization
    public void saveToFile(List<Student> students) throws JacksonException {
        objectMapper.writeValue(studentsFile, students);
    }

    // Load list of students from JSON file
    public List<Student> loadFromFile() throws JacksonException {
        if (!studentsFile.exists()) {
            System.out.println("Students file doesn't exist yet. Creating a new one...");
            return new ArrayList<>();
        }

        return new ArrayList<>(Arrays.asList(objectMapper.readValue(studentsFile, Student[].class)));
    }
}