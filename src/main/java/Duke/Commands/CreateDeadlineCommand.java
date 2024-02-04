package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Deadline;
import Duke.Tasks.Task;
import Duke.Ui;

import java.time.DateTimeException;

/**
 * Represents a command to create a deadline task.
 */
public class CreateDeadlineCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;
    String input;

    /**
     * Constructor for the DeadlineCommand class.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The taskList object to record the tasks.
     * @param storage The Storage object to save and load information.
     * @param input The user input.
     */
    public CreateDeadlineCommand(Ui ui, TaskList taskList, Storage storage, String input) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.input = input;
    }

    @Override
    public void execute() throws DukeException {
        Task addedTask = null;
        String[] inputArr = input.split(" ");
        String commandWord = inputArr[0];
        try {
            addedTask = new Deadline(getTaskName(commandWord, this.input), getEndDate(commandWord, input));
            taskList.addTask(addedTask);
            this.ui.printAddMessage(taskList.size(), addedTask);
        } catch (DateTimeException dte) {
            this.ui.printDateErrorMessage("Please follow the format for date and time (d/M/yyyy HHmm).");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
