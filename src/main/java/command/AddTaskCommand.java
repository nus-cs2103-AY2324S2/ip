package command;

import duke.Storage;
import model.Task;
import model.TaskList;
import view.AddTaskView;

/**
 * Command to add a {@code Task} to the {@code TaskList}.
 */
public class AddTaskCommand extends Command {
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    private final Task task;
    private final TaskList taskList;

    /**
     * Constructs an {@code AddTaskCommand} object with the specified {@code Task} and {@code TaskList}.
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
     * Executes the {@code AddTaskCommand}, performing the necessary actions to carry out the specific functionality
     * related to adding a task.
     *
     * @param storage The storage object that manages the data persistence. It is used to update
     *                and save changes after executing the command.
     */
    @Override
    public void execute(Storage storage) {
        taskList.add(task);
        storage.update(taskList.getTaskList());
        AddTaskView addTaskView = new AddTaskView(this.task, this.taskList);
        addTaskView.display();
    }
}
