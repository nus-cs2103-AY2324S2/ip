package seedu.duke;
import java.util.ArrayList;

import seedu.duke.command.Command;
import seedu.duke.common.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.StorageOperationException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;


/**
 * The Duke program implements a chat bot that allows users to manage their tasks
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Boolean isExit = false;
    private String loadingExceptionMessage;
    /**
     * Constructor of the Duke program, it would create a new Ui, and load tasks that users have from storage
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();


        try {
            taskList = new TaskList(storage.load());
        } catch (StorageOperationException e) {
            ui.getLoadingExceptionMessage();
            loadingExceptionMessage = e.getMessage();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public String getInitMessage() {
        return this.ui.getWelcomeMessage();
    }

    public String getLoadingExceptionMessage() {
        return this.loadingExceptionMessage;
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(taskList, ui, storage);
            isExit = command.isExit();
            return ui.getResponse();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

}

