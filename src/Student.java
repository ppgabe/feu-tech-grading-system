import java.util.ArrayList;
import java.util.List;

public abstract class Student extends Person {
    private static final long serialVersionUID = 1L;
    protected List<CourseGrade> courseGrades = new ArrayList<>();

    public Student(String id, String name) {
        super(id, name);
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

        courseGrades.add(new CourseGrade(courseName, units, gradePoint));
    }

    public List<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    public double getGWA() {
        if (courseGrades.isEmpty()) return 0.0;

        double weightedSum = 0.0;
        int totalUnits = 0;

        for (CourseGrade cg : courseGrades) {
            weightedSum += cg.getGradePoint() * cg.getUnits();
            totalUnits += cg.getUnits();
        }

        if (totalUnits == 0) return 0.0;

        return weightedSum / totalUnits;
    }

    public abstract String calculateFinalGrade();

    @Override
    public String toString() {
        return super.toString() +
                "\nType: " + getClass().getSimpleName() +
                "\nGWA: " + String.format("%.2f", getGWA()) +
                "\nStanding: " + calculateFinalGrade() +
                "\nCourses: " + courseGrades;
    }
}