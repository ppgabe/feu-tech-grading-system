import java.util.List;

public interface GradeCalculator {
    default double calculateGWA(List<CourseGrade> grades) {

        if (grades.isEmpty()) return 0.0;

        double weightedSum = 0.0;
        int totalUnits = 0;

        for (CourseGrade cg : grades) {
            weightedSum += cg.gradePoint() * cg.units();
            totalUnits += cg.units();
        }

        if (totalUnits == 0) return 0.0;

        // Cast totalUnits to double first to avoid problems with integer division.
        return weightedSum / (double) totalUnits;
    }

    String getProficiency(List<CourseGrade> grades, double gwa);
}
