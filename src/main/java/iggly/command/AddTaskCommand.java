package iggly.command;

import iggly.duke.Storage;
import iggly.model.Task;
import iggly.model.TaskList;
import iggly.view.AddTaskView;

/**
 * Command to add a {@link Task} to the {@link TaskList}.
 */
public class AddTaskCommand extends Command {
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    public static final String SCHEDULE = "schedule";
    private final Task task;
    private final TaskList taskList;

    /**
     * Constructs an {@link AddTaskCommand} object with the specified {@link Task} and {@link TaskList}.
     * This command is used to add a new task to the given task list.
     *
     * @param task The task to be added, in the form of {@code ToDo}, {@code Event} or {@code Deadline}.
     * @param taskList The task list to which the task will be added.
     */
    public AddTaskCommand(Task task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }

    /**
     * Executes the {@link AddTaskCommand}, performing the necessary actions to carry out the specific functionality
     * related to adding a task.
     * <p>
     * This method adds the given task to the task list, update the storage and display the add task message by
     * initialising the add task view.
     *
     * @param storage The storage object that manages the data persistence. It is used to update
     *                and save changes after executing the command.
     */
    @Override
    public String execute(Storage storage) {
        taskList.add(task);
        storage.updateFile(taskList.getTaskList());
        AddTaskView addTaskView = new AddTaskView(this.task, this.taskList);
        return addTaskView.display();
    }
}
