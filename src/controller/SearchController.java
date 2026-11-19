//package sample.controller;
//
//public class SearchController {
//
//    private final StudentContext context;
//
//    public SearchController(StudentContext context) {
//
//        this.context = context;
//
//        setupSearch();
//    }
//
//    private void setupSearch() {
//
//        context.getView()
//                .getSearchField()
//                .textProperty()
//                .addListener(
//                        (observable, oldValue, newValue) -> {
//
//                            String search =
//                                    newValue
//                                            .toLowerCase()
//                                            .trim();
//
//                            context.getFilteredStudents().setPredicate(
//                                    student ->
//                                            student.getName()
//                                                    .toLowerCase()
//                                                    .startsWith(search)
//                            );
//                        }
//                );
//    }
//}
