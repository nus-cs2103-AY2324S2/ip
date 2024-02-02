package seiki.commands;

import java.util.ArrayList;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.data.task.ToDo;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'todo' command.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private ArrayList<String> args;

    public ToDoCommand(ArrayList<String> args) {
        this.args = args;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        String taskTitle = String.join(" ", this.args);
        ToDo task = new ToDo(taskTitle);
        taskList.addTask(task);
        storage.save(taskList);
        ui.showAddTask(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
