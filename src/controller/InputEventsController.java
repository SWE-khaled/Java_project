//package sample.controller;
//
//import javafx.scene.input.KeyCode;
//import sample.view.StudentView;
//
//// Mouse and keyboard events on the search field
////public class InputEventsController {
//
//    private final StudentView view;
//
//    public InputEventsController(StudentView view) {
//
//        this.view = view;
//
//        setupMouseEvents();
//
//        setupKeyboardEvents();
//    }
//
//    // =========================================================
//    // MOUSE EVENTS
//    // =========================================================
//
//    private void setupMouseEvents() {
//
//        view.getSearchField().setOnMousePressed(event ->
//                System.out.println("Mouse Pressed"));
//
//        view.getSearchField().setOnMouseReleased(event ->
//                System.out.println("Mouse Released"));
//
//        view.getSearchField().setOnMouseEntered(event ->
//                System.out.println("Mouse Entered Search Field"));
//
//        view.getSearchField().setOnMouseExited(event ->
//                System.out.println("Mouse Exited Search Field"));
//
//        view.getSearchField().setOnMouseMoved(event ->
//                System.out.println("Mouse Moved"));
//
//        view.getSearchField().setOnMouseDragged(event ->
//                System.out.println("Mouse Dragged"));
//
//        // Mouse Clicked: double click clears the search
//        view.getSearchField().setOnMouseClicked(event -> {
//
//            System.out.println("Mouse Clicked");
//
//            if (event.getClickCount() == 2) {
//                view.getSearchField().clear();
//            }
//        });
//    }
//
//    // =========================================================
//    // KEYBOARD EVENTS
//    // =========================================================
//
//    private void setupKeyboardEvents() {
//
//        view.getSearchField().setOnKeyPressed(event -> {
//
//            System.out.println(
//                    "Key Pressed: " + event.getCode()
//            );
//
//            // Press ESC to clear search
//            if (event.getCode() == KeyCode.ESCAPE) {
//                view.getSearchField().clear();
//            }
//        });
//
//        view.getSearchField().setOnKeyReleased(event ->
//                System.out.println(
//                        "Key Released: " + event.getCode()
//                ));
//
//        view.getSearchField().setOnKeyTyped(event ->
//                System.out.println(
//                        "Key Typed: " + event.getCharacter()
//                ));
//    }
//}
