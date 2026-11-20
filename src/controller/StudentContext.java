package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import model.Student;
import service.StudentService;
import view.StudentView;

// Shared state used by all the feature controllers
public class StudentContext {

    private final StudentView view;
    private final StudentService service;

    private final ObservableList<Student> allStudents;
    private final FilteredList<Student> filteredStudents;

    public StudentContext() {

        view =
                new StudentView();

        service =
                new StudentService();

        allStudents =
                FXCollections.observableArrayList(
                        service.loadStudents()
                );

        // All -> Filtered (search) -> Sorted (column click) -> Table
        filteredStudents =
                new FilteredList<>(allStudents, student -> true);

        SortedList<Student> sortedStudents =
                new SortedList<>(filteredStudents);

        sortedStudents.comparatorProperty().bind(
                view.getStudentTable().comparatorProperty()
        );

        view.getStudentTable()
                .setItems(sortedStudents);
    }

    // =========================================================
    // GETTERS
    // =========================================================

    public StudentView getView() {
        return view;
    }

    public StudentService getService() {
        return service;
    }

    public ObservableList<Student> getAllStudents() {
        return allStudents;
    }

    public FilteredList<Student> getFilteredStudents() {
        return filteredStudents;
    }

    // =========================================================
    // SHARED HELPERS
    // =========================================================

    public void updateStatistics() {

        int excellent = 0;
        int atRisk = 0;

        for (Student student : allStudents) {

            if (student.isExcellent()) {
                excellent++;
            }

            if (student.isAtRisk()) {
                atRisk++;
            }
        }

        view.updateStatistics(
                allStudents.size(),
                excellent,
                atRisk
        );
    }

    public int nextId() {

        int max = 0;

        for (Student student : allStudents) {

            if (student.getId() > max) {
                max = student.getId();
            }
        }

        return max + 1;
    }

    // Returns the selected student, or null after showing a message
    public Student getSelectedStudent() {

        Student selected =
                view.getStudentTable()
                        .getSelectionModel()
                        .getSelectedItem();

        if (selected == null) {

            AlertHelper.warning(
                    "No Student Selected",
                    "Please select a student first."
            );
        }

        return selected;
    }
}
