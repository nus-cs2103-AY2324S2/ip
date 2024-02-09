import haro.Haro;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * The Duke class represents the main entry point for the Duke application.
 *
 */
public class Duke extends Application {

    public Duke() {
        // New duke instance
    }

    /**
     * Main method to execute the Duke application.
     * Initializes the Duke application with an instance of the Haro class
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Haro haro = new Haro("data/saveList.txt", "data/");
        haro.initialise();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
