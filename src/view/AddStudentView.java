package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddStudentView {

    private final Stage stage;

    private final TextField nameField;
    private final TextField ageField;
    private final TextField gpaField;

    private final Button saveButton;
    private final Button cancelButton;

    public AddStudentView() {
        stage = new Stage();
        stage.setTitle("Add Student");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label title = new Label("Add New Student");

        Label nameLabel = new Label("Student Name");
        Label ageLabel = new Label("Age");
        Label gpaLabel = new Label("GPA (0 - 4)");

        nameField = new TextField();
        nameField.setPromptText("Enter student name");

        ageField = new TextField();
        ageField.setPromptText("Enter age");

        gpaField = new TextField();
        gpaField.setPromptText("Enter GPA");

        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(15);

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);
        form.add(ageLabel, 0, 1);
        form.add(ageField, 1, 1);
        form.add(gpaLabel, 0, 2);
        form.add(gpaField, 1, 2);

        saveButton = new Button("Save Student");
        cancelButton = new Button("Cancel");

        HBox buttons = new HBox(10);
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
