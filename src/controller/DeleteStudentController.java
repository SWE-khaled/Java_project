package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Student;

import java.util.Optional;

public class DeleteStudentController {

    private final StudentContext context;

    public DeleteStudentController(StudentContext context) {
        this.context = context;
        setupDeleteButton();
    }

    private void setupDeleteButton() {
        context.getView()
                .getDeleteButton()
                .setOnAction(event -> deleteSelectedStudent());
    }

    private void deleteSelectedStudent() {
        Student selectedStudent = context.getSelectedStudent();

        if (selectedStudent == null) {
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Student");
        confirm.setHeaderText(null);
        confirm.setContentText("Are you sure you want to delete "
                + selectedStudent.getName()
                + " ?");

        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            context.getService().deleteStudent(selectedStudent.getId());
            context.getAllStudents().remove(selectedStudent);
            context.clearSearch();
            context.updateStatistics();

            AlertHelper.info("Success", "Student deleted successfully.");
        }
    }
}
