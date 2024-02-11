package cat.command;

import java.util.ArrayList;

import cat.Storage;
import cat.TaskList;
import cat.task.Task;
import cat.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "The task list must not be null";
        assert ui != null : "The ui must not be null";

        ArrayList<Integer> missingIndices = new ArrayList<>();

        var builder = new StringBuilder();
        indices.stream().sorted().forEach(
                index -> {
                    try {
                        Task task = tasks.deleteTask(index);
                        builder.append(index + 1).append(" ").append(task).append("\n");
                    } catch (TaskList.TaskNotFound e) {
                        missingIndices.add(index);
                    }
                }
        );

        if (builder.length() != 0) {
            builder.insert(0, "Deleted tasks:\n");
        }

        if (!missingIndices.isEmpty()) {
            builder.append("Could not find tasks with indices: ").append(missingIndices);
        }

        ui.showNote(builder.toString());
    }
}
