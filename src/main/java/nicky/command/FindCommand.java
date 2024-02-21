package nicky.command;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import nicky.NickyException;
import nicky.Ui;
import nicky.task.Storage;
import nicky.task.Task;
import nicky.task.TaskList;

/**
 * Represents a command to find tasks in the Nicky application.
 * It searches for tasks that contain a specific keyword in their descriptions.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new FindCommand instance with the specified keyword.
     *
     * @param fullCommand The full command string entered by the user.
     */
    public FindCommand(String fullCommand) {
        String[] commandParts = fullCommand.split(" ", 2);
        this.keyword = commandParts.length > 1 ? commandParts[1].trim() : "";
    }

    /**
     * Executes the FindCommand to search for tasks that contain the keyword in their descriptions.
     *
     * @param tasks   The TaskList containing the list of tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for saving and loading tasks.
     * @throws NickyException If there is an issue executing the command.
     * @throws IOException  If there is an issue saving the tasks to the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NickyException, IOException {
        if (keyword.isEmpty()) {
            throw new NickyException("Please provide a keyword to search for.");
        }

        List<Task> filteredTasks = tasks.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        if (filteredTasks.isEmpty()) {
            return "No tasks found with the keyword: " + keyword;
        } else {
            String matchedTasks = IntStream.range(0, filteredTasks.size())
                    .mapToObj(index -> (index + 1) + ". " + filteredTasks.get(index).toString())
                    .collect(Collectors.joining("\n"));
            return "Here are the matching tasks in your list:\n" + matchedTasks;
        }
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
