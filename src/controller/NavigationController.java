package controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Student;

import java.util.Optional;

public class NavigationController {

    private final StudentContext context;

    public NavigationController(StudentContext context) {
        this.context = context;
        setupNavigation();
    }

    private void setupNavigation() {
        context.getView().getHomeButton().setOnAction(event -> resetView());

        context.getView().getStudentsButton().setOnAction(event -> {
            resetView();
            context.getView().getStudentTable().requestFocus();
        });

        context.getView().getReportsButton().setOnAction(event -> showReport());

        context.getView().getSettingsButton().setOnAction(event ->
                AlertHelper.info("Settings", "No settings are available in this version."));

        context.getView().getLogoutButton().setOnAction(event -> confirmExit());
    }

    private void resetView() {
        context.getView().getSearchField().clear();
        context.getView().getStudentTable().getSelectionModel().clearSelection();
    }

    private void showReport() {
        int total = context.getAllStudents().size();
        int excellent = 0;
        int atRisk = 0;

        for (Student student : context.getAllStudents()) {
            if (student.isExcellent()) {
                excellent++;
            }

            if (student.isAtRisk()) {
                atRisk++;
            }
        }

        AlertHelper.info(
                "Reports",
                "Total students: " + total
                        + "\nExcellent: " + excellent
                        + "\nAt risk: " + atRisk
        );
    }

    private void confirmExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to exit the application?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}
