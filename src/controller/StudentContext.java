package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import model.Student;
import service.StudentService;
import view.StudentView;

public class StudentContext {

    private final StudentView view;
    private final StudentService service;

    private final ObservableList<Student> allStudents;
    private final FilteredList<Student> filteredStudents;

    public StudentContext() {
        view = new StudentView();
        service = new StudentService();

        allStudents = FXCollections.observableArrayList(service.loadStudents());

        filteredStudents = new FilteredList<>(allStudents, student -> true);

        SortedList<Student> sortedStudents = new SortedList<>(filteredStudents);
        sortedStudents.comparatorProperty().bind(view.getStudentTable().comparatorProperty());
        view.getStudentTable().setItems(sortedStudents);
    }

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

        view.updateStatistics(allStudents.size(), excellent, atRisk);
    }

    public int nextId() {
        return service.getNextId();
    }

    public void clearSearch() {
        view.getSearchField().clear();
    }

    public Student getSelectedStudent() {
        Student selected = view.getStudentTable()
                .getSelectionModel()
                .getSelectedItem();

        if (selected == null) {
            AlertHelper.warning("No Student Selected", "Please select a student first.");
        }

        return selected;
    }
}
