package com.xenodirt;

import com.xenodirt.CourseGrade;
import com.xenodirt.CourseGradeList;

public interface GradeCalculator {
    default double calculateGWA(CourseGradeList grades) {

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

    String getProficiency(CourseGradeList gradeList, double gwa);
}
