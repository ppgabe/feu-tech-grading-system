package com.xenodirt;

import com.xenodirt.CourseGradeList;

import java.io.Serializable;

public record Student(String id, String name, StudentType type,
                      CourseGradeList courseGradeList) implements Serializable {

    @Override
    public String toString() {
        double gwa = type.getGradeCalculator().calculateGWA(courseGradeList);

        return String.format(
            """
                ID: %s
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
            type.getGradeCalculator().getProficiency(courseGradeList, gwa)
            );
    }
}