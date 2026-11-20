package controller;

import model.Student;

public class StudentValidator {

    public static Student build(int id, String name, String ageText, String gpaText) {
        if (name == null || name.trim().isEmpty()) {
            AlertHelper.error("Invalid Data", "Student name cannot be empty.");
            return null;
        }

        if (name.contains(",")) {
            AlertHelper.error("Invalid Data", "Student name cannot contain a comma.");
            return null;
        }

        try {
            int age = Integer.parseInt(ageText);
            double gpa = Double.parseDouble(gpaText);

            if (age < 16 || age > 60) {
                AlertHelper.error("Invalid Data", "Age must be between 16 and 60.");
                return null;
            }

            if (gpa < 0 || gpa > 4) {
                AlertHelper.error("Invalid Data", "GPA must be between 0 and 4.");
                return null;
            }

            return new Student(id, name, age, gpa);
        } catch (NumberFormatException e) {
            AlertHelper.error(
                    "Invalid Data",
                    "Age must be an integer and GPA must be a number."
            );
            return null;
        }
    }
}
