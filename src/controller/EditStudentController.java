package controller;

import model.Student;
import view.EditStudentView;

public class EditStudentController {

    private final StudentContext context;

    public EditStudentController(StudentContext context) {

        this.context = context;

        setupEditButton();
    }

    private void setupEditButton() {

        context.getView()
                .getEditButton()
                .setOnAction(event -> editSelectedStudent());
    }

    private void editSelectedStudent() {

        Student selectedStudent =
                context.getSelectedStudent();

        if (selectedStudent == null) {
            return;
        }

        EditStudentView editView =
                new EditStudentView(selectedStudent);

        editView.getSaveButton()
                .setOnAction(event ->
                        saveEditedStudent(selectedStudent, editView)
                );

        editView.getCancelButton()
                .setOnAction(event ->
                        editView.getStage().close()
                );

        editView.show();
    }

    private void saveEditedStudent(
            Student oldStudent,
            EditStudentView editView) {

        Student updatedStudent =
                StudentValidator.build(
                        oldStudent.getId(),
                        editView.getNameField().getText().trim(),
                        editView.getAgeField().getText().trim(),
                        editView.getGpaField().getText().trim()
                );

        if (updatedStudent == null) {
            return;
        }

        context.getService().updateStudent(updatedStudent);

        int index =
                context.getAllStudents().indexOf(oldStudent);

        context.getAllStudents().set(index, updatedStudent);

        context.updateStatistics();

        editView.getStage().close();

        AlertHelper.info(
                "Success",
                "Student updated successfully."
        );
    }
}
