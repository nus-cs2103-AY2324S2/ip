package duke.commands;

import java.time.DateTimeException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Represents a command to create an event task.
 */
public class CreateEventCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private String input;

    /**
     * Constructor for the CreateEventCommand class.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The taskList object to record the tasks.
     * @param storage The Storage object to save and load information.
     * @param input The user input.
     */
    public CreateEventCommand(Ui ui, TaskList taskList, Storage storage, String input) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.input = input;
    }

    @Override
    public String execute() throws DukeException {
        Task addedTask = null;
        String[] inputArr = input.split(" ");
        String commandWord = inputArr[0];
        try {
            addedTask = new Event(getTaskName(commandWord, this.input),
                    getStartDate(this.input), getEndDate(commandWord, input));
            taskList.addTask(addedTask);
            return this.ui.printAddMessage(taskList.size(), addedTask);
        } catch (DateTimeException dte) {
            return this.ui.printDateErrorMessage("Please follow the format for date and time (d/M/yyyy HHmm).");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
