package judy.commands;

import judy.storage.Storage;
import judy.task.Task;
import judy.task.TaskList;
import judy.ui.Ui;

/**
 * To add a {@link Task} to the {@link TaskList}.
 */
public class AddTaskCommand extends Command {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    private final Task task; // The task to be added
    private final TaskList taskList; // The list to which the task will be added

    /**
     * Constructs an Add Task Command with the specified task and task list.
     *
     * @param task      The task to be added.
     * @param taskList  The list to which the task will be added.
     */
    public AddTaskCommand(Task task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }

    /**
     * Executes the command by adding the task to the task list,
     * display add task message and update the storage.
     *
     * @param storage The storage component responsible for saving the task list.
     * @param ui The user interface component for displaying messages to the user.
     */
    @Override
    public String execute(Storage storage, Ui ui) {
        taskList.add(this.task);
        storage.save(taskList.getTaskList());
        return ui.showAddTask(this.task, this.taskList.getSize());
    }

    /**
     * Indicates whether this command represents an exit command.
     * In this case, always returns false as adding a task does not trigger an exit.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
