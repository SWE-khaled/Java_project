package controller;

import javafx.scene.input.KeyCode;
import view.StudentView;

public class InputEventsController {

    private final StudentView view;

    public InputEventsController(StudentView view) {
        this.view = view;
        setupMouseEvents();
        setupKeyboardEvents();
    }

    private void setupMouseEvents() {
        view.getSearchField().setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                view.getSearchField().clear();
            }
        });
    }

    private void setupKeyboardEvents() {
        view.getSearchField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                view.getSearchField().clear();
            }
        });
    }
}
