package com.xenodirt;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "students.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        StudentFileManager studentFileManager = new StudentFileManager();

        // Load existing students from file
        try {
            List<Student> loaded = studentFileManager.loadFromFile(DATA_FILE);

            manager.setStudents(loaded);

            System.out.println("Loaded " + loaded.size() + " students from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load existing data: " + e.getMessage());
        }

        int choice;

        do {
            printMenu();

            choice = getIntInput(sc, "Enter choice: ");

            System.out.println();

            switch (choice) {
                case 1:
                    addStudent(sc, manager);
                    pressEnterToReturn(sc);

                    break;
                case 2:
                    addGradeToStudent(sc, manager);
                    pressEnterToReturn(sc);

                    break;
                case 3:
                    viewStudent(sc, manager);
                    pressEnterToReturn(sc);

                    break;
                case 4:
                    manager.listAllStudents();
                    pressEnterToReturn(sc);

                    break;
                case 5:
                    removeStudent(sc, manager);
                    pressEnterToReturn(sc);

                    break;
                case 6:
                    saveData(manager);
                    pressEnterToReturn(sc);

                    break;
                case 7:
                    saveData(manager);

                    System.out.println("Exiting...");

                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }


}