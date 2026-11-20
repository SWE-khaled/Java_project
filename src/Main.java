import controller.StudentController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        StudentController controller = new StudentController();

        stage.setTitle("FCI Student Care");
        stage.setScene(new Scene(controller.getView().getRoot(), 1100, 700));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
