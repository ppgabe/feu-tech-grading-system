package com.xenodirt;

public class UndergraduateStudentGradeCalculator implements GradeCalculator {

    @Override
    public String getProficiency(CourseGradeList gradeList, double gwa) {
        if (gwa == 0.0 && gradeList.isEmpty()) return "N/A";

        if (gwa == 4.0) return "Excellent";
        else if (gwa == 3.5) return "Superior";
        else if (gwa == 3.0) return "Very Good";
        else if (gwa == 2.5) return "Good";
        else if (gwa == 2.0) return "Satisfactory";
        else if (gwa == 1.5) return "Fair";
        else if (gwa == 1.0) return "Pass";
        else return "Failed";
    }
}