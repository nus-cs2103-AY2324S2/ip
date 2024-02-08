package duke;

import duke.parser.InputException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Represents task tracking bot.
 */
public class Duke extends Application {
    private Ui ui = null;
    private Parser parser = null;
    private TaskList taskList = null;
    private Storage storage = null;

    private enum LivState {
        ACTIVE,
        INACTIVE
    }
    private static Duke instance = null;
    private LivState currentState = null;

    public Duke() {
        // break the initialisation into the initialization function of different classes
        currentState = LivState.INACTIVE;

        // additional line of code to accommodate the need to have a publicly available constructor
        instance = this;
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        helloWorld.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        Label notHelloWorld = new Label("not Hello World!"); // Creating a new Label control
        notHelloWorld.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        Scene secondaryScene = new Scene(notHelloWorld); // Setting the scene to be our Label

        Stage secondaryStage = new Stage();
        secondaryStage.setScene(secondaryScene); // Setting the stage to show our screen
        secondaryStage.show(); // Render the stage.
    }

    /**
     * Starts Duke.
     * Initialises ui, parser, taskList and storage.
     * Loads taskList saved locally if any.
     * Creates a Data directory to host the data file if local taskList not found.
     * Starts listening to user input.
     */
    private void instanceStart() {
        // initialize duke.ui.Ui
        ui = Ui.getInstance();
        ui.initUi();

        // initialize parser
        parser = Parser.getInstance();
        parser.initParser();

        // initialize taskList
        taskList = TaskList.getInstance();
        taskList.initTaskList();

        // initialize storage
        storage = Storage.getInstance();
        storage.initStorage();

        try {
            storage.loadFromMemory();
        } catch (FileNotFoundException e) {
            System.out.println("No previous task file found");
            storage.createDataFile();
        }

        instance.ToggleActiveState();

        while (isActive()) {
            // should start the cycle talking
            String userInput = ui.StartListening();
            try {
                parser.ProcessInput(userInput);
            } catch (InputException e) {
                ui.speak(e.getMessage());
            }
        }
    }

    public boolean isActive() {
        return currentState == LivState.ACTIVE;
    }
    public static void main(String[] args) {
        getInstance().instanceStart();
    }

    /**
     * Toggles active state of Duke.
     */
    public void ToggleActiveState() {
        if (currentState != LivState.INACTIVE) {
            currentState = LivState.INACTIVE;
            return;
        }

        if (currentState == LivState.INACTIVE) {
            currentState = LivState.ACTIVE;
            return;
        }
    }

    public static Duke getInstance() {
        if (instance == null) {
            instance = new Duke();
        }
        return instance;
    }
}
