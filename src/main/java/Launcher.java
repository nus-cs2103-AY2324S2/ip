import GUI.Main;
import javafx.application.Application;
//import GUI.Main;


/**
 * The Launcher class serves as the entry point for launching the Duke application.
 * It contains the main method, which invokes the Application. launch method
 * to start the JavaFX application represented by the Duke class.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
