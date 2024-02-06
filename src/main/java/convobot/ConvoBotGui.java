package convobot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The main class for the ConvoBot GUI application.
 */
public class ConvoBotGui extends Application {

    private final ConvoBot convoBot = new ConvoBot("./data/tasks.txt");

    public ConvoBotGui() {
        // ...
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        // ...
    }
}
