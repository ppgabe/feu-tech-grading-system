import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CourseGradeList implements Iterable<CourseGrade> {
    private final List<CourseGrade> grades;

    public CourseGradeList() {
        this.grades = new ArrayList<>();
    }

    public CourseGradeList(List<CourseGrade> grades) {
        this.grades = grades;
    }

    public void addCourseGrade(String courseName, int units, double gradePoint) throws InvalidGradeException {
        if (gradePoint < 0.5 || gradePoint > 4.0)
            throw new InvalidGradeException("Grade must be between 0.5 and 4.0.");

        // Check if grades are a multiple of 0.5
        double scaled = gradePoint * 2;

        if (Math.round(scaled) != scaled)
            throw new InvalidGradeException("Grade must be in 0.5 intervals (e.g., 0.5, 1.0, 1.5, ..., 4.0).");

        if (units <= 0)
            throw new InvalidGradeException("Units must be positive.");

        grades.add(new CourseGrade(courseName, units, gradePoint));
    }

    public List<CourseGrade> getGrades() {
        return Collections.unmodifiableList(grades);
    }

    public boolean isEmpty() {
        return grades.isEmpty();
    }

    @Override
    public Iterator<CourseGrade> iterator() {
        return grades.iterator();
    }
}
