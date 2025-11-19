import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    // Save list of students to file using serialization
    public static void saveToFile(String filename, List<Student> students) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
        }
    }

    // Load list of students from file
    @SuppressWarnings("unchecked")
    public static List<Student> loadFromFile(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Student>) ois.readObject();
        }
    }
}