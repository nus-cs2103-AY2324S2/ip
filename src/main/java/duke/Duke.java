package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.exceptions.DukeException;
import duke.exceptions.StorageException;
import duke.task.TaskList;

/**
 * Driver code for chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a Duke object with a given datapath.
     *
     * @param filePath Filepath of the datafile.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.taskList = this.storage.load();
        } catch (StorageException e) {
//            this.ui.showError(e);
            //todo: ask the user if want to create new datafile, possibly deleting old data
        }
    }

    /** Initializes and runs the chatbot program */
//    public void run() {
//        ui.showWelcome();
//        ui.showLine();
//        ui.showList(taskList);
//        ui.showLine();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = this.ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(taskList, ui, storage);
//                isExit = c instanceof ByeCommand;
//            } catch (DukeException e) {
//                ui.showError(e);
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

    /**
     *
     */
    public String getGreeting() {
        StringBuilder greeting = new StringBuilder("Hello and welcome! I'm fakegpt!\n");
        greeting.append(this.taskList.toString());
        return greeting.toString();
    }

    /**
     * Returns output from chatbot given an input.
     *
     * @param input String from user.
     * @return
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(this.taskList, this.storage);
            return c.getResponse();
        } catch (DukeException e) {
            return  e.getMessage();
        }
    }
}
