package com.xenodirt;

import tools.jackson.core.JacksonException;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        File studentsFile = FileUtils.getFileBesideJar("students.json");
        StudentFileManager studentFileManager = new StudentFileManager(studentsFile);

        // Load existing students from file
        try {
            List<Student> loaded = studentFileManager.loadFromFile();

            manager.setStudents(loaded);

            System.out.println("Loaded " + loaded.size() + " students from file.");
        } catch (JacksonException e) {
            System.out.println("Could not load existing data: " + e.getMessage());
        }

        var system = new GradingSystem(sc, manager, studentFileManager);

        system.run();

        sc.close();
    }
}