package duke;

import java.io.FileNotFoundException;

import duke.parser.InputException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents task tracking bot.
 */
public class Duke {
    private static Duke instance = null;
    private Ui ui = null;
    private Parser parser = null;
    private TaskList taskList = null;
    private Storage storage = null;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return parser.processInputReturnString(input);
        } catch (InputException e) {
            return e.getMessage();
        }
    }

    /**
     * Initialize UI, parser, tasklist and storage components of Duke
     */
    public void initDukeLogic() {
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

    public static Duke getInstance() {
        if (instance == null) {
            instance = new Duke();
        }
        instance.initDukeLogic();
        return instance;
    }
}