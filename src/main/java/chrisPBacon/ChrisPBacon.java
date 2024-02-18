package chrisPBacon;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import task.TaskList;
import util.Storage;
import util.Ui;
import util.UserInput;

/**
 *  This class contains the main method for the chatbot, ChrisP Bacon.
 *  ChrisP Bacon is a chatbot that manages the user's list of tasks.
 */
public class ChrisPBacon extends Application {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Initialises new ui and storage objects, loads tasks into a new task list object.
     */
    public ChrisPBacon() {
        ui = new Ui();
        storage = new Storage("data/list.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.printError("Oink! File not found :(\n");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot program.
     */
    public void run() {
        ui.printIntro();

        UserInput userInput = new UserInput();
        while (!userInput.isInputBye()) {
            userInput.processInput(ui, this.tasks);
        }
        // if user entered "bye", save list and exit chatbot.
        ui.printBye();
        storage.save(this.tasks);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
