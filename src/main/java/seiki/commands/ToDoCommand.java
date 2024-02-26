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
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " [TASK_TITLE]";
    public static final String COMMAND_HELPER = "Please use the following format: " + COMMAND_FORMAT;
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": Creates a deadline task.\n"
            + "Parameters: TASK_TITLE\n"
            + "Example: " + COMMAND_WORD + " read book /by 2022/02/22 1234";
    private final String taskTitle;

    public ToDoCommand(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
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
