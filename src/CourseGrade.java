import java.io.Serializable;

public class CourseGrade implements Serializable {
    private static final long serialVersionUID = 1L;
    private String courseName;
    private int units;
    private double gradePoint;

    public CourseGrade(String courseName, int units, double gradePoint) {
        this.courseName = courseName;
        this.units = units;
        this.gradePoint = gradePoint;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getUnits() {
        return units;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    @Override
    public String toString() {
        return courseName + " (" + units + " units, " + gradePoint + ")";
    }
}