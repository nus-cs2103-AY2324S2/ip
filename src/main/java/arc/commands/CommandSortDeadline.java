package arc.commands;

import java.util.ArrayList;
import java.util.Comparator;

import arc.exceptions.ArcException;
import arc.storage.Storage;
import arc.tasks.Deadline;
import arc.tasks.Task;
import arc.tasks.TaskList;

/**
 * Represents the command for sorting deadline tasks list in the Arc application.
 */
public class CommandSortDeadline extends Command {

    /**
     * Constructs a new CommandSortDeadline object.
     */
    public CommandSortDeadline() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws ArcException {
        ArrayList<Integer> deadlineIndexes = new ArrayList<>();
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();

        // Populate deadlineIndexes and deadlineTasks
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                deadlineIndexes.add(i);
                deadlineTasks.add(deadline);
            }
        }

        deadlineTasks.sort(Comparator.comparing(Deadline::getBy));

        // Re-populate deadline tasks at sorted indexes
        for (int i = 0; i < deadlineIndexes.size(); i++) {
            int currIndex = deadlineIndexes.get(i);
            Deadline deadline = deadlineTasks.get(i);

            tasks.set(currIndex, deadline);
        }

        storage.saveTasks(tasks);

        return String.format("Got it. I've sorted %d deadline tasks.", deadlineTasks.size());
    }
}
