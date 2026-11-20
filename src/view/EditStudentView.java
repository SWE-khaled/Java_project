package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Student;

public class EditStudentView {

    private final Stage stage;

    private final TextField nameField;
    private final TextField ageField;
    private final TextField gpaField;

    private final Button saveButton;
    private final Button cancelButton;

    public EditStudentView(Student student) {
        stage = new Stage();
        stage.setTitle("Edit Student");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label title = new Label("Edit Student (ID: " + student.getId() + ")");

        Label nameLabel = new Label("Student Name");
        Label ageLabel = new Label("Age");
        Label gpaLabel = new Label("GPA (0 - 4)");

        nameField = new TextField();
        ageField = new TextField();
        gpaField = new TextField();

        nameField.setText(student.getName());
        ageField.setText(String.valueOf(student.getAge()));
        gpaField.setText(String.valueOf(student.getGpa()));

        saveButton = new Button("Save Changes");
        cancelButton = new Button("Cancel");

        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(15);

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);
        form.add(ageLabel, 0, 1);
        form.add(ageField, 1, 1);
        form.add(gpaLabel, 0, 2);
        form.add(gpaField, 1, 2);

        VBox buttons = new VBox(10);
        buttons.setAlignment(Pos.CENTER_RIGHT);
        buttons.getChildren().addAll(saveButton, cancelButton);

        VBox root = new VBox(20);
        root.setPadding(new Insets(25));
        root.getChildren().addAll(title, form, buttons);

        Scene scene = new Scene(root, 450, 350);
        stage.setScene(scene);
    }

    public Stage getStage() {
        return stage;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getAgeField() {
        return ageField;
    }

    public TextField getGpaField() {
        return gpaField;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public void show() {
        stage.showAndWait();
    }
}
