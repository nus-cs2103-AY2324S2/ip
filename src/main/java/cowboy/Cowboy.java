package cowboy;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class
 */
public class Cowboy extends Application {
    private Storage storage;
    private ArrayList<Task> taskList = new ArrayList<>();
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    /**
     * Constructor for Duke
     */
    public Cowboy() {
        this.ui = new Ui();
        this.storage = new Storage(taskList);
        storage.loadTaskList();
        this.tasks = new TaskList(storage, taskList);
        this.parser = new Parser(tasks);
    }

    /**
     * Starts the program
     *
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     * @throws StringIndexOutOfBoundsException if input is not a number
     */
    public void start() {

        ui.showGreeting();

        String input = ui.getUserInput();
        while (true) {
            ui.printLine();
            if (!parser.executeUserInput(input)) {
                break;
            }

            input = ui.getUserInput();
        }
        ui.showGoodbye();
        ui.closeScanner();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        Cowboy mainApp = new Cowboy();
        mainApp.start();
    }
}
