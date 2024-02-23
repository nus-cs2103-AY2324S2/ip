package henry.command;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;
import henry.task.Task;

/**
 * Represents a command to update a task.
 */
public class UpdateCommand extends Command {
    private final int index;
    private final String description;
    private final String to;
    private final String from;
    private final String by;

    /**
     * Creates an UpdateCommand object.
     * @param params The parameters of the command.
     */
    public UpdateCommand(String params) throws HenryException {
        String[] parts = params.split(" ", 2);

        try {
            this.index = Integer.parseInt(parts[0]) - 1;
        } catch (NumberFormatException e) {
            throw new HenryException("Index provided is not a number");
        }

        int descriptionIndex = parts[1].indexOf("/d");
        int toIndex = parts[1].indexOf("/to");
        int fromIndex = parts[1].indexOf("/from");
        int byIndex = parts[1].indexOf("/by");

        description = descriptionIndex != -1 ? parts[1].substring(descriptionIndex + 3) : null;
        to = toIndex != -1 ? parts[1].substring(toIndex + 4) : null;
        from = fromIndex != -1 ? parts[1].substring(fromIndex + 6) : null;
        by = byIndex != -1 ? parts[1].substring(byIndex + 4) : null;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws HenryException {
        Task updatedTask = tasks.updateTask(index, description, to, from, by);
        storage.save(tasks);
        return String.format("Edited task: %s\n", updatedTask);
    }
}
