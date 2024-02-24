package arc.commands;

import java.util.ArrayList;

import arc.exceptions.ArcException;
import arc.storage.Storage;
import arc.tasks.Task;
import arc.tasks.TaskList;

/**
 * Represents the command for finding tasks that contain a specific keyword in the Arc application.
 */
public class CommandFind extends Command {

    private final String keyword;

    /**
     * Constructs a new CommandFind object with the specified keyword to search for in tasks.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public CommandFind(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws ArcException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");

        ArrayList<String> matches = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(this.keyword)) {
                matches.add(String.format("%d. %s", i + 1, task));
            }
        }
        String matchesOutput = String.join("\n", matches);
        stringBuilder.append(matchesOutput);

        return stringBuilder.toString();
    }
}
