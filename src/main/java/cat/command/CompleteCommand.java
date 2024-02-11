package cat.command;

import java.util.ArrayList;

import cat.Storage;
import cat.TaskList;
import cat.task.Task;
import cat.ui.Ui;

/**
 * A command to change the completion status of a task.
 */
public class CompleteCommand extends Command {
    private final ArrayList<Integer> indices;
    private final boolean isComplete;

    /**
     * Constructs a command that changes the completion status of tasks.
     *
     * @param indices    the indices of the tasks to change
     * @param isComplete whether to change the tasks to completed leave it as pending
     */
    public CompleteCommand(ArrayList<Integer> indices, boolean isComplete) {
        this.indices = indices;
        this.isComplete = isComplete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "The task list must not be null";
        assert ui != null : "The ui must not be null";
        assert storage != null : "The storage must not be null";

        ArrayList<Integer> missingIndices = new ArrayList<>();

        var builder = new StringBuilder();
        indices.stream().sorted().forEach(
                index -> {
                    try {
                        Task task = tasks.getTask(index);
                        task.setStatus(isComplete);
                        builder.append(index + 1).append(" ").append(task).append("\n");
                    } catch (TaskList.TaskNotFound e) {
                        missingIndices.add(index);
                    }
                }
        );

        if (builder.length() != 0) {
            builder.insert(0, "Modified tasks:\n");
        }

        if (!missingIndices.isEmpty()) {
            builder.append("Could not find tasks with indices: ").append(missingIndices);
        }

        ui.showNote(builder.toString());
    }
}
