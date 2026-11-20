package controller;

public class SearchController {

    private final StudentContext context;

    public SearchController(StudentContext context) {
        this.context = context;
        setupSearch();
    }

    private void setupSearch() {
        context.getView()
                .getSearchField()
                .textProperty()
                .addListener((observable, oldValue, newValue) -> applySearch(newValue));

        context.getView()
                .getSearchButton()
                .setOnAction(event -> applySearch(context.getView().getSearchField().getText()));
    }

    private void applySearch(String text) {
        String search = text == null ? "" : text.toLowerCase().trim();

        context.getFilteredStudents().setPredicate(student ->
                student.getName().toLowerCase().startsWith(search)
        );
    }
}
