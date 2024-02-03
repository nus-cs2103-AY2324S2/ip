package Duke.Commands;

import Duke.DukeException;
import Duke.TaskList;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;
import Duke.Ui;
import Duke.Storage;

import java.time.DateTimeException;

public class AddCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;
    String input;
    public AddCommand(Ui ui, TaskList taskList, Storage storage, String input) {
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
            if (commandWord.equalsIgnoreCase("todo")) {
                addedTask = new Todo(getTaskName(commandWord, this.input));
            } else if (commandWord.equalsIgnoreCase("deadline")) {
                addedTask = new Deadline(getTaskName(commandWord, this.input), getEndDate(commandWord, input));
            } else if (commandWord.equalsIgnoreCase("event")) {
                addedTask = new Event(getTaskName(commandWord, this.input),
                        getStartDate(this.input), getEndDate(commandWord, input));
            } else {
                throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
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
