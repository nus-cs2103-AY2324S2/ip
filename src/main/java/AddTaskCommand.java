import java.io.IOException;
import java.util.ArrayList;

public class AddTaskCommand extends Command {
    private Zack.TaskType taskType;
    String description;

    public AddTaskCommand(String description, Zack.TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
    }

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
