package controller;

import model.Student;

public class ViewStudentController {

    private final StudentContext context;

    public ViewStudentController(StudentContext context) {

        this.context = context;

        setupViewButton();
    }

    private void setupViewButton() {

        context.getView()
                .getViewButton()
                .setOnAction(event -> viewSelectedStudent());
    }

    private void viewSelectedStudent() {

        Student student =
                context.getSelectedStudent();

        if (student == null) {
            return;
        }

        AlertHelper.info(
                "Student Details",
                "ID:      " + student.getId()
                        + "\nName:   " + student.getName()
                        + "\nAge:      " + student.getAge()
                        + "\nGPA:     " + student.getGpa()
                        + "\nStatus:  " + student.getStatus()
        );
    }
}
