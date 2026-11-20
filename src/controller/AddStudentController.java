package controller;

import model.Student;
import view.AddStudentView;

public class AddStudentController {

    private final StudentContext context;

    public AddStudentController(StudentContext context) {
        this.context = context;
        setupAddButton();
    }

    private void setupAddButton() {
        context.getView()
                .getAddButton()
                .setOnAction(event -> addStudent());
    }

    private void addStudent() {
        AddStudentView addView = new AddStudentView();

        addView.getSaveButton().setOnAction(event -> saveNewStudent(addView));
        addView.getCancelButton().setOnAction(event -> addView.getStage().close());

        addView.show();
    }

    private void saveNewStudent(AddStudentView addView) {
        Student student = StudentValidator.build(
                context.nextId(),
                addView.getNameField().getText().trim(),
                addView.getAgeField().getText().trim(),
                addView.getGpaField().getText().trim()
        );

        if (student == null) {
            return;
        }

        context.getService().addStudent(student);
        context.getService().saveLastId(student.getId());
        context.getAllStudents().add(student);
        context.clearSearch();
        context.updateStatistics();

        addView.getStage().close();

        AlertHelper.info("Success", "Student added successfully.");
    }
}
