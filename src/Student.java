import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record Student(String id, String name, StudentType type,
                      List<CourseGrade> courseGrades) implements Serializable {

    @Override
    public String toString() {
        double gwa = type.getGradeCalculator().calculateGWA(courseGrades);

        return String.format(
            """
                ID: %d
                Name: %s
                Type: %s
                -------------
                GWA: %.2f
                Proficiency: %s
                -------------
                """,
            id,
            name,
            type,
            gwa,
            type.getGradeCalculator().getProficiency(courseGrades, gwa)
            );
    }
}