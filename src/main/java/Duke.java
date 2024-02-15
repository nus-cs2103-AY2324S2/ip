import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import parser.Parser;

/**
 * The main class for duke chat bot.
 */

public class Duke extends Application {
    public Duke() {

    }

    public static void main(String[] args) {
        Ui duke = new Ui("Zizhen");
        Storage storage = new Storage("./data/duke.txt");
        duke.greeting();

        ArrayList<Task> temp = new ArrayList<>();
        temp = storage.getHistory();
        TaskList todoList = new TaskList(temp);

        Parser parser = new Parser(todoList, storage);
        parser.parse();

        duke.exit();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
