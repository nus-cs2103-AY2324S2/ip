package commands;

import destiny.DestinyException;
import destiny.Task;
import destiny.TaskList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Command that finds all relevant tasks in the tasklist.
 */
public class FindCmd extends Command {
    private String details;

    /**
     * Constructor for the Find class.
     *
     * @param details The string used to Find tasks.
     */
    public FindCmd(String details) {
        this.details = details;
    }

    /**
     * Finds relevant tasks and
     * collates these tasks into a string.
     *
     * @return String of found elements from the tasklist.
     * @throws DestinyException when no details were provided by the user.
     */
    @Override
    public String execute(TaskList tasks) throws DestinyException {
        if (details == "" || details == null) {
            throw new DestinyException("Please enter a description for the Find command");
        }

        List<Task> matches = tasks.getTaskList().stream().filter(task -> task.getDescription().toLowerCase()
                    .contains(details.toLowerCase())).collect(Collectors.toList());

        if (tasks.size() == 0) {
            return "There's nothing in your list so far";
        }

        if (matches.size() == 0) {
            return "There's nothing in your list that matches your search";
        }

        String results = "Here are the matching tasks in your list:";
        for (int i = 0; i < matches.size(); i++) {
            int j = i + 1;
            results += "\n" + j + ". " + matches.get(i).toString();
        }
        return results;
    }
}
