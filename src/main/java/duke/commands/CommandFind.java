package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the command for finding tasks that contain a specific keyword in the Duke application.
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
     * Executes the command to find tasks containing the specified keyword and displays the matching tasks to the user.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface component for displaying messages to the user.
     * @param storage The storage component for saving the task list.
     * @throws DukeException If there is an error while searching for tasks or displaying the results.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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

        ui.showMessage(stringBuilder.toString());
    }
}
