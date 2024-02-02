package zack.commands;

import zack.Zack;
import zack.ZackException;
import zack.tasks.Deadline;
import zack.tasks.Event;
import zack.tasks.Task;
import zack.tasks.Todo;
import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

import java.io.IOException;

/**
 * Command class responsible for adding tasks to the task list.
 */
public class AddTaskCommand extends Command {
    private Zack.TaskType taskType;
    String description;

    /**
     * Constructs an AddTaskCommand with the specified task description and type.
     *
     * @param description The description of the task.
     * @param taskType    The type of task (Todo, Deadline, or Event).
     */
    public AddTaskCommand(String description, Zack.TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
    }

    /**
     * Executes the AddTaskCommand, adding a new task to the task list.
     *
     * @param tasks   The TaskList to which the task will be added.
     * @param ui      The Ui to display user messages.
     * @param storage The Storage to save the updated task list.
     * @throws ZackException If there is an error adding the task.
     * @throws IOException   If there is an error in saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZackException, IOException {
        Task newTask;
        if (taskType == Zack.TaskType.TODO) {
            newTask = new Todo(description, false);
        } else if (taskType == Zack.TaskType.DEADLINE) {
            String[] parts = description.split(" /by ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new ZackException("The deadline command is incomplete or incorrectly formatted. " +
                        "Please include '/by' followed by the deadline.");
            }
            newTask = new Deadline(parts[0], parts[1], false);
        } else {
            // EVENT
            String[] parts = description.split(" /from ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new ZackException("The event command is incomplete. " +
                        "Please include '/from' followed by the start time.");
            }
            String[] times = parts[1].split(" /to ");
            if (times.length < 2 || times[1].trim().isEmpty()) {
                throw new ZackException("The event command is incomplete. " +
                        "Please include '/to' followed by the end time.");
            }
            newTask = new Event(parts[0], times[0], times[1], false);
        }

        tasks.addTask(newTask);
        ui.showAddedTask(newTask, tasks.getSize());
        storage.save(tasks.getAllTasks());
    }
}
