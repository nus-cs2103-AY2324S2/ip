package seiki.commands;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.data.task.ToDoTask;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'todo' command.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_HELPER = "Please use the following format: todo [task title]";
    public static final String COMMAND_WORD = "todo";
    private final String taskTitle;

    public ToDoCommand(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
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
