package duke;

import duke.parser.InputException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;

/**
 * Represents task tracking bot.
 */
public class Duke {
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return parser.ProcessInputReturnString(input);
        } catch (InputException e) {
            return e.getMessage();
        }
    }

    public void initDukeLogic() {
        currentState = LivState.INACTIVE;

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
    }

    /**
     * Starts Duke.
     * Initialises ui, parser, taskList and storage.
     * Loads taskList saved locally if any.
     * Creates a Data directory to host the data file if local taskList not found.
     * Starts listening to user input.
     */
    /*
    private void Start() {
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
            try{
                parser.ProcessInput(userInput);
            } catch (InputException e) {
                ui.speak(e.getMessage());
            }
        }
    }
     */
    public boolean isActive() {
        return currentState == LivState.ACTIVE;
    }

    /*
    public static void main(String[] args) {
        getInstance().instanceStart();
    }
     */

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
        instance.initDukeLogic();
        return instance;
    }
}
