package cat.command;

import java.util.ArrayList;

import cat.Storage;
import cat.TaskList;
import cat.task.Task;
import cat.ui.Ui;
import cat.ui.response.Response;

/**
 * A command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final ArrayList<Integer> indices;

    /**
     * Construct a command that deletes a task at the given index.
     */
    public DeleteCommand(ArrayList<Integer> indices) {
        this.indices = indices;
    }

    @Override
    public Response execute(TaskList tasks, Storage storage) {
        assert tasks != null : "The task list must not be null";

        ArrayList<Integer> missingIndices = new ArrayList<>();

        var builder = new StringBuilder();
        indices.stream().sorted((a, b) -> b - a).forEach(
                index -> {
                    try {
                        Task task = tasks.deleteTask(index);
                        builder.append(index + 1).append(" ").append(task).append("\n");
                    } catch (TaskList.TaskNotFound e) {
                        missingIndices.add(index);
                    }
                }
        );

        if (!missingIndices.isEmpty()) {
            return Ui.showError(new Exception("Could not find tasks with indices: " + missingIndices));
        }

        if (builder.length() != 0) {
            builder.insert(0, "Deleted tasks:\n");
        }

        return Ui.showNote(builder.toString());
    }
}
