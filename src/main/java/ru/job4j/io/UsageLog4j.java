package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String nameStudent = "Petr Arsentev";
        int age = 33;
        byte course = 3;
        char gender = 'M';
        double grade = 4.5;
        float height = 1.80f;
        long scholarship = 500000000;
        boolean militaryDepartment = true;
        float weight = 80;

        LOG.debug("Student info: name-{}, age-{}, course-{}, gender-{}, "
                + "grade-{}, height-{}, scholarship-{}, militaryDepartment-{}, weight-{}",
                  nameStudent, age, course, gender, grade, height,
                scholarship, militaryDepartment, weight);
        LOG.error("Student info: name-{}, age-{}, course-{}, gender-{}, "
                        + "grade-{}, height-{}, scholarship-{}, militaryDepartment-{}, weight-{}",
                nameStudent, age, course, gender, grade, height,
                scholarship, militaryDepartment, weight);
        LOG.info("Student info: name-{}, age-{}, course-{}, gender-{}, "
                        + "grade-{}, height-{}, scholarship-{}, militaryDepartment-{}, weight-{}",
                nameStudent, age, course, gender, grade, height,
                scholarship, militaryDepartment, weight);
        LOG.warn("Student info: name-{}, age-{}, course-{}, gender-{}, "
                        + "grade-{}, height-{}, scholarship-{}, militaryDepartment-{}, weight-{}",
                nameStudent, age, course, gender, grade, height,
                scholarship, militaryDepartment, weight);

    }
}