package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;
import Duke.Ui;

import java.time.DateTimeException;

public class CreateDeadlineCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;
    String input;
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
