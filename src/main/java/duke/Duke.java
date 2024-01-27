package duke;

import duke.command.Command;
import duke.common.TaskList;
import duke.exception.DukeException;
import duke.exception.StorageOperationException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * The Duke program implements a chat bot that allows users to manage their tasks
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor of the Duke program, it would create a new Ui, and load tasks that users have from storage
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (StorageOperationException e) {
            ui.showLoadingException();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Run the Duke Program by reading command from the users and executing it
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Calls run method of the Duke Program to starts the program
     * @param args
     */
    public static void main(String[] args) {
        //        String logo = " ____        _        \n"
        //                + "|  _ \\ _   _| | _____ \n"
        //                + "| | | | | | | |/ / _ \\\n"
        //                + "| |_| | |_| |   <  __/\n"
        //                + "|____/ \\__,_|_|\\_\\___|\n";
        //        System.out.println("Hello from\n" + logo);

        new Duke().run();

    }
}

