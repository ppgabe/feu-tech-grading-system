package com.xenodirt;

import java.io.Serializable;

public record CourseGrade(String courseName, int units, double gradePoint) implements Serializable {

    @Override
    public String toString() {
        return courseName + " (" + units + " units, " + gradePoint + ")";
    }
}