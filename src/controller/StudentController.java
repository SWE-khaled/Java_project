package controller;

import view.StudentView;

public class StudentController {

    private final StudentContext context;

    private final SearchController searchController;
    private final NavigationController navigationController;
    private final AddStudentController addController;
    private final EditStudentController editController;
    private final DeleteStudentController deleteController;
    private final ViewStudentController viewController;
    private final InputEventsController inputEventsController;

    public StudentController() {
        context = new StudentContext();

        searchController = new SearchController(context);
        navigationController = new NavigationController(context);
        addController = new AddStudentController(context);
        editController = new EditStudentController(context);
        deleteController = new DeleteStudentController(context);
        viewController = new ViewStudentController(context);
        inputEventsController = new InputEventsController(context.getView());

        context.updateStatistics();
    }

    public StudentView getView() {
        return context.getView();
    }
}
