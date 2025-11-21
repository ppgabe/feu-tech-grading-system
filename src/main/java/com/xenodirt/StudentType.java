package com.xenodirt;

import com.xenodirt.GradeCalculator;
import com.xenodirt.GraduateStudentGradeCalculator;

public enum StudentType {
    UNDERGRADUATE("Undergraduate", new UndergraduateStudentGradeCalculator()),
    GRADUATE("Graduate", new GraduateStudentGradeCalculator());

    private final String formattedName;
    private final GradeCalculator gradeCalculator;

    private StudentType(String s, GradeCalculator gc) {
        this.formattedName = s;
        this.gradeCalculator = gc;
    }

    public GradeCalculator getGradeCalculator() {
        return gradeCalculator;
    }

    @Override
    public String toString() {
        return this.formattedName;
    }
}
