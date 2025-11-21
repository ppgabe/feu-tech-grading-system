import java.util.List;

public interface GradeCalculator {
    double calculateGWA(List<CourseGrade> grades);

    String getProficiency(double gwa);
}
