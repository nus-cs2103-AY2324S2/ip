package seiki.commands;

import java.util.ArrayList;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.data.task.ToDoTask;
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
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        String taskTitle = String.join(" ", args);
        assert !taskTitle.isEmpty() : "Task title should not be empty";
        ToDoTask task = new ToDoTask(taskTitle);
        taskList.addTask(task);
        storage.save(taskList);
        return ui.showAddTask(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
