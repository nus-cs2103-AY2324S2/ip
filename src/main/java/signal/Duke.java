package signal;

import signal.task.Task;

import signal.util.Parser;
import signal.util.Storage;
import signal.util.Ui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private static Storage fileManager = new Storage();
    private static ArrayList<Task> taskList = fileManager.loadTasks();
    private static Ui ui = new Ui(taskList, fileManager);
    public static Parser parser = new Parser(taskList, ui);

    public Duke() {

    }

    public static void main(String[] args) {
        ui.intro();


        while(true) {
            String userInput = ui.scan();

            if (userInput.equals("bye")) {
                // Exit program
                break;
            }
            parser.read(userInput);
        }
        ui.leave();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

    }
}

