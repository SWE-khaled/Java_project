//package sample.controller;
//
//import sample.model.Student;
//
//public class StudentValidator {
//
//    // Returns a valid Student, or null after showing an error
//    public static Student build(
//            int id,
//            String name,
//            String ageText,
//            String gpaText) {
//
//        if (name.isEmpty()) {
//
//            AlertHelper.error(
//                    "Invalid Data",
//                    "Student name cannot be empty."
//            );
//
//            return null;
//        }
//
//        if (name.contains(",")) {
//
//            AlertHelper.error(
//                    "Invalid Data",
//                    "Student name cannot contain a comma."
//            );
//
//            return null;
//        }
//
//        try {
//
//            int age =
//                    Integer.parseInt(ageText);
//
//            double gpa =
//                    Double.parseDouble(gpaText);
//
//            if (age < 16 || age > 60) {
//
//                AlertHelper.error(
//                        "Invalid Data",
//                        "Age must be between 16 and 60."
//                );
//
//                return null;
//            }
//
//            if (gpa < 0 || gpa > 4) {
//
//                AlertHelper.error(
//                        "Invalid Data",
//                        "GPA must be between 0 and 4."
//                );
//
//                return null;
//            }
//
//            return new Student(id, name, age, gpa);
//
//        } catch (NumberFormatException e) {
//
//            AlertHelper.error(
//                    "Invalid Data",
//                    "Age must be an integer and GPA must be a number."
//            );
//
//            return null;
//        }
//    }
//}
