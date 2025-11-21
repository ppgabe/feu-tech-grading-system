import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record Student(String id, String name, StudentType type, List<CourseGrade> courseGrades) implements Serializable {

    @Override
    public String toString() {
        return String.format(
            """
                ID: %d
                Name: %s
                Type: %s
                """,
            id,
            name,
            type
            );
    }
}