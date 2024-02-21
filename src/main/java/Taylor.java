import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class to execute Taylor ChatBot
 */
public class Taylor extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Container container = new Container();
        container.setUp(stage);
        container.formatWindow(stage);
        container.handleInput(stage);
    }
}
