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

public class CreateTodoCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;
    String input;
    public CreateTodoCommand(Ui ui, TaskList taskList, Storage storage, String input) {
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
        addedTask = new Todo(getTaskName(commandWord, this.input));
        taskList.addTask(addedTask);
        this.ui.printAddMessage(taskList.size(), addedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
