//package sample.view;
//
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.VBox;
//
//import sample.model.Student;
//
//public class StudentView {
//
//    // =========================
//    // Main Root
//    // =========================
//
//    private final BorderPane root;
//
//    // =========================
//    // Controls
//    // =========================
//
//    private TextField searchField;
//
//    private TableView<Student> studentTable;
//
//    private Button addButton;
//    private Button editButton;
//    private Button deleteButton;
//    private Button viewButton;
//
//    private Label totalValue;
//    private Label excellentValue;
//    private Label atRiskValue;
//
//    public StudentView() {
//
//        root = new BorderPane();
//
//        createLayout();
//    }
//
//    // =========================
//    // Main Layout
//    // =========================
//
//    private void createLayout() {
//
//        root.setTop(createHeader());
//
//        root.setLeft(createSidebar());
//
//        root.setCenter(createMainContent());
//    }
//
//    // =========================================================
//    // HEADER
//    // =========================================================
//
//    private HBox createHeader() {
//
//        Label logo =
//                new Label("FCI STUDENT CARE");
//
//        Label subtitle =
//                new Label("Student Management System");
//
//        VBox branding =
//                new VBox(3);
//
//        branding.getChildren().addAll(
//                logo,
//                subtitle
//        );
//
//        Label user =
//                new Label("Admin  ● Online");
//
//        HBox header =
//                new HBox();
//
//        header.setAlignment(
//                Pos.CENTER_LEFT
//        );
//
//        header.setPadding(
//                new Insets(15, 25, 15, 25)
//        );
//
//        header.setSpacing(20);
//
//        header.getChildren().addAll(
//                branding,
//                user
//        );
//
//        HBox.setHgrow(
//                branding,
//                Priority.ALWAYS
//        );
//
//        return header;
//    }
//
//    // =========================================================
//    // SIDEBAR
//    // =========================================================
//
//    private VBox createSidebar() {
//
//        VBox sidebar =
//                new VBox(10);
//
//        sidebar.setPadding(
//                new Insets(20)
//        );
//
//        sidebar.setPrefWidth(220);
//
//        // -------------------------
//        // Dashboard Section
//        // -------------------------
//
//        Label dashboardTitle =
//                new Label("DASHBOARD");
//
//        Button homeButton =
//                createMenuButton("🏠  Home");
//
//        Button studentsButton =
//                createMenuButton("🎓  Students");
//
//        Button reportsButton =
//                createMenuButton("📊  Reports");
//
//        // -------------------------
//        // Management Section
//        // -------------------------
//
//        Label managementTitle =
//                new Label("MANAGEMENT");
//
//        addButton =
//                createMenuButton("➕  Add Student");
//
//        editButton =
//                createMenuButton("✏  Edit Student");
//
//        deleteButton =
//                createMenuButton("🗑  Delete Student");
//
//        viewButton =
//                createMenuButton("👁  View Student");
//
//        Button settingsButton =
//                createMenuButton("⚙  Settings");
//
//        // -------------------------
//        // Spacer
//        // -------------------------
//
//        VBox spacer =
//                new VBox();
//
//        VBox.setVgrow(
//                spacer,
//                Priority.ALWAYS
//        );
//
//        // -------------------------
//        // Logout
//        // -------------------------
//
//        Button logoutButton =
//                createMenuButton("🚪  Logout");
//
//        sidebar.getChildren().addAll(
//
//                dashboardTitle,
//
//                homeButton,
//                studentsButton,
//                reportsButton,
//
//                managementTitle,
//
//                addButton,
//                editButton,
//                deleteButton,
//                viewButton,
//                settingsButton,
//
//                spacer,
//
//                logoutButton
//        );
//
//        return sidebar;
//    }
//
//    // =========================================================
//    // MENU BUTTON
//    // =========================================================
//
//    private Button createMenuButton(String text) {
//
//        Button button =
//                new Button(text);
//
//        button.setMaxWidth(
//                Double.MAX_VALUE
//        );
//
//        button.setPrefHeight(42);
//
//        button.setAlignment(
//                Pos.CENTER_LEFT
//        );
//
//        return button;
//    }
//
//    // =========================================================
//    // MAIN CONTENT
//    // =========================================================
//
//    private VBox createMainContent() {
//
//        VBox content =
//                new VBox(20);
//
//        content.setPadding(
//                new Insets(30)
//        );
//
//        Label title =
//                new Label("Student Search");
//
//        Label description =
//                new Label(
//                        "Search and manage registered students"
//                );
//
//        VBox titleSection =
//                new VBox(5);
//
//        titleSection.getChildren().addAll(
//                title,
//                description
//        );
//
//        HBox searchSection =
//                createSearchSection();
//
//        HBox statistics =
//                createStatistics();
//
//        Label tableTitle =
//                new Label("Registered Students");
//
//        TableView<Student> table =
//                createStudentTable();
//
//        content.getChildren().addAll(
//
//                titleSection,
//
//                searchSection,
//
//                statistics,
//
//                tableTitle,
//
//                table
//        );
//
//        VBox.setVgrow(
//                table,
//                Priority.ALWAYS
//        );
//
//        return content;
//    }
//
//    // =========================================================
//    // SEARCH AREA
//    // =========================================================
//
//    private HBox createSearchSection() {
//
//        searchField =
//                new TextField();
//
//        searchField.setPromptText(
//                "Search student name..."
//        );
//
//        searchField.setPrefHeight(45);
//
//        Button searchButton =
//                new Button("Search");
//
//        searchButton.setPrefHeight(45);
//
//        HBox searchBox =
//                new HBox(10);
//
//        searchBox.setAlignment(
//                Pos.CENTER_LEFT
//        );
//
//        searchBox.getChildren().addAll(
//                searchField,
//                searchButton
//        );
//
//        HBox.setHgrow(
//                searchField,
//                Priority.ALWAYS
//        );
//
//        return searchBox;
//    }
//
//    // =========================================================
//    // STATISTICS CARDS
//    // =========================================================
//
//    private HBox createStatistics() {
//
//        HBox cards =
//                new HBox(15);
//
//        totalValue =
//                new Label("0");
//
//        excellentValue =
//                new Label("0");
//
//        atRiskValue =
//                new Label("0");
//
//        cards.getChildren().addAll(
//
//                createCard("Total Students", totalValue),
//
//                createCard("Excellent (GPA ≥ 3.5)", excellentValue),
//
//                createCard("At Risk (GPA < 2.0)", atRiskValue)
//        );
//
//        return cards;
//    }
//
//    private VBox createCard(
//            String title,
//            Label valueLabel
//    ) {
//
//        Label titleLabel =
//                new Label(title);
//
//        VBox card =
//                new VBox(8);
//
//        card.setPadding(
//                new Insets(15)
//        );
//
//        card.setPrefWidth(200);
//
//        card.getChildren().addAll(
//                titleLabel,
//                valueLabel
//        );
//
//        return card;
//    }
//
//    // Called by the controller whenever the data changes
//    public void updateStatistics(
//            int total,
//            int excellent,
//            int atRisk
//    ) {
//
//        totalValue.setText(String.valueOf(total));
//
//        excellentValue.setText(String.valueOf(excellent));
//
//        atRiskValue.setText(String.valueOf(atRisk));
//    }
//
//    // =========================================================
//    // STUDENT TABLE
//    // =========================================================
//
//    private TableView<Student> createStudentTable() {
//
//        studentTable =
//                new TableView<>();
//
//        TableColumn<Student, Integer> idColumn =
//                new TableColumn<>("ID");
//
//        idColumn.setCellValueFactory(
//                new PropertyValueFactory<>("id")
//        );
//
//        TableColumn<Student, String> nameColumn =
//                new TableColumn<>("Student");
//
//        nameColumn.setCellValueFactory(
//                new PropertyValueFactory<>("name")
//        );
//
//        TableColumn<Student, Integer> ageColumn =
//                new TableColumn<>("Age");
//
//        ageColumn.setCellValueFactory(
//                new PropertyValueFactory<>("age")
//        );
//
//        TableColumn<Student, Double> gpaColumn =
//                new TableColumn<>("GPA");
//
//        gpaColumn.setCellValueFactory(
//                new PropertyValueFactory<>("gpa")
//        );
//
//        TableColumn<Student, String> statusColumn =
//                new TableColumn<>("Status");
//
//        statusColumn.setCellValueFactory(
//                new PropertyValueFactory<>("status")
//        );
//
//        studentTable.getColumns().addAll(
//
//                idColumn,
//
//                nameColumn,
//
//                ageColumn,
//
//                gpaColumn,
//
//                statusColumn
//        );
//
//        idColumn.setPrefWidth(70);
//
//        nameColumn.setPrefWidth(250);
//
//        ageColumn.setPrefWidth(100);
//
//        gpaColumn.setPrefWidth(100);
//
//        statusColumn.setPrefWidth(180);
//
//        studentTable.setColumnResizePolicy(
//                TableView.CONSTRAINED_RESIZE_POLICY
//        );
//
//        return studentTable;
//    }
//
//    // =========================================================
//    // GETTERS
//    // =========================================================
//
//    public BorderPane getRoot() {
//        return root;
//    }
//
//    public TextField getSearchField() {
//        return searchField;
//    }
//
//    public TableView<Student> getStudentTable() {
//        return studentTable;
//    }
//
//    public Button getAddButton() {
//        return addButton;
//    }
//
//    public Button getEditButton() {
//        return editButton;
//    }
//
//    public Button getDeleteButton() {
//        return deleteButton;
//    }
//
//    public Button getViewButton() {
//        return viewButton;
//    }
//}
