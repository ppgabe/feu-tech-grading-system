import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentManager {
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public void setStudents(List<Student> list) {
        if (list == null) students = new ArrayList<>();
        else students = list;
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public Student findStudentById(String id) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.id().equals(id)) return s;
        }

        throw new StudentNotFoundException("Student with ID " + id + " not found.");
    }

    public void removeStudentById(String id) throws StudentNotFoundException {
        Student s = findStudentById(id);

        students.remove(s);
    }

    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) System.out.println(s);
        }
    }
}