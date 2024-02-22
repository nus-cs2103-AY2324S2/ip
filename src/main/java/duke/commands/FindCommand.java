package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Represent a command to find tasks.
 */
public class FindCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private String input;
    /**
     * Constructor for a command to find tasks.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The taskList containing tasks.
     * @param storage The Storage object that saves and loads information.
     * @param input The user input.
     */
    public FindCommand(Ui ui, TaskList taskList, Storage storage, String input) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.input = input;
    }

    @Override
    public String execute() throws DukeException {
        if (this.input.length() < 6) {
            throw new DukeException("Invalid input, please enter the task you want to search");
        }
        String keyWord = this.input.substring(5);
        return this.ui.printFoundTasks(this.taskList, keyWord);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
