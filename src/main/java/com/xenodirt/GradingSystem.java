package com.xenodirt;

import tools.jackson.core.JacksonException;

import java.io.File;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GradingSystem implements Runnable {
    private final Scanner sc;
    private final StudentManager manager;
    private final StudentFileManager studentFileManager;
    private final File studentsFile;

    public GradingSystem(Scanner sc, StudentManager manager, StudentFileManager studentFileManager, File studentsFile) {
        this.sc = sc;
        this.manager = manager;
        this.studentFileManager = studentFileManager;
        this.studentsFile = studentsFile;
    }

    @Override
    public void run() {

    }

    private void printMenu() {
        System.out.println("\nFEU TECH GRADING SYSTEM");
        System.out.println("[1] Add student");
        System.out.println("[2] Add grade to student");
        System.out.println("[3] View student details");
        System.out.println("[4] List all students");
        System.out.println("[5] Remove student");
        System.out.println("[6] Save data to file");
        System.out.println("[7] Exit");
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);

                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                System.out.print("Enter grade (0.5 - 4.0): ");

                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter student ID: ");

        String id = sc.nextLine();

        System.out.print("Enter student name: ");

        String name = sc.nextLine();

        Arrays.stream(StudentType.values())
            .forEachOrdered(
                type -> System.out.printf("[%d] %s\n", type.ordinal() + 1, type)
            );

        int inputType = getIntInput("Choose student type: ");

        try {
            Student s = Arrays.stream(StudentType.values())
                .filter(type -> type.ordinal() + 1 == inputType)
                .findFirst()
                .map(type -> new Student(id, name, type, new CourseGradeList()))
                .orElseThrow();

            manager.addStudent(s);
        } catch (NoSuchElementException nsee) {
            System.out.printf("Invalid input: [%d] is not a valid choice\n", inputType);
            return;
        }

        System.out.println("Student added successfully!");
    }

    private void addGradeToStudent() {
        System.out.print("Enter student ID: ");

        String id = sc.nextLine();

        try {
            Student s = manager.findStudentById(id);

            System.out.print("Enter course name: ");
            String courseName = sc.nextLine();

            int units = getIntInput("Enter number of units: ");

            double gradePoint = getDoubleInput();

            s.courseGradeList().addCourseGrade(courseName, units, gradePoint);
            System.out.println("Course grade added successfully!");

        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidGradeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void viewStudent() {
        System.out.print("Enter student ID: ");
        String id = sc.nextLine();
        try {
            Student s = manager.findStudentById(id);
            System.out.println(s);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeStudent() {
        System.out.print("Enter student ID: ");
        String id = sc.nextLine();
        try {
            manager.removeStudentById(id);
            System.out.println("Student removed successfully!");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveData() {
        try {
            studentFileManager.saveToFile(studentsFile, manager.getStudents());

            System.out.println("Data file saved successfully!");
        } catch (JacksonException je) {
            System.out.println("Unable to save to data file: " + je.getMessage());
        }
    }

    private void pressEnterToReturn() {
        System.out.print("Press enter to return...");
        sc.nextLine();
    }
}
