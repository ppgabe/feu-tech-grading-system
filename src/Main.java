import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "students.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        // Load existing students from file
        try {
            List<Student> loaded = FileStorage.loadFromFile(DATA_FILE);

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

    private static void printMenu() {
        System.out.println("\nFEU TECH GRADING SYSTEM");
        System.out.println("[1] Add student");
        System.out.println("[2] Add grade to student");
        System.out.println("[3] View student details");
        System.out.println("[4] List all students");
        System.out.println("[5] Remove student");
        System.out.println("[6] Save data to file");
        System.out.println("[7] Exit");
    }

    private static int getIntInput(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);

                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double getDoubleInput(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);

                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void addStudent(Scanner sc, StudentManager manager) {
        System.out.print("Enter student ID: ");

        String id = sc.nextLine();

        System.out.print("Enter student name: ");

        String name = sc.nextLine();

        Arrays.stream(StudentType.values())
            .forEachOrdered(
                type -> System.out.printf("[%d] %s\n", type.ordinal() + 1, type.toString())
            );

        int inputType = getIntInput(sc, "Choose student type: ");

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

    private static void addGradeToStudent(Scanner sc, StudentManager manager) {
        System.out.print("Enter student ID: ");

        String id = sc.nextLine();

        try {
            Student s = manager.findStudentById(id);

            System.out.print("Enter course name: ");
            String courseName = sc.nextLine();

            int units = getIntInput(sc, "Enter number of units: ");

            double gradePoint = getDoubleInput(sc, "Enter grade (0.5 - 4.0): ");

            s.courseGradeList().addCourseGrade(courseName, units, gradePoint);
            System.out.println("Course grade added successfully!");

        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidGradeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void viewStudent(Scanner sc, StudentManager manager) {
        System.out.print("Enter student ID: ");
        String id = sc.nextLine();
        try {
            Student s = manager.findStudentById(id);
            System.out.println(s);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeStudent(Scanner sc, StudentManager manager) {
        System.out.print("Enter student ID: ");
        String id = sc.nextLine();
        try {
            manager.removeStudentById(id);
            System.out.println("Student removed successfully!");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void saveData(StudentManager manager) {
        try {
            FileStorage.saveToFile(DATA_FILE, manager.getStudents());
            System.out.println("Data saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void pressEnterToReturn(Scanner sc) {
        System.out.print("Press enter to return...");
        sc.nextLine();
    }
}