package ficin;

import java.util.List;
import java.util.stream.Collectors;

import ficin.task.Task;
import ficin.task.TaskList;

/**
 * Represents a command to search for tasks based on a provided keyword or query.
 * This class extends the abstract Command class and implements the search functionality
 * to find and display tasks that contain the query within their description.
 */
public class FindCommand extends Command {

    /**
     * Executes the "find" command to search for tasks in the given TaskList
     * based on the user's input query. It identifies tasks that contain the
     * query string in their description as represented by their toString() method.
     *
     * @param taskList  The TaskList containing the tasks to be searched.
     * @param ui        The Ui interface to interact with the user.
     * @param userInput The complete user input string including the command and the search query.
     * @throws DukeException If the search query is not provided or an error occurs during execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
        if (userInput.trim().equals("find")) {
            throw new DukeException("Please enter a search query after 'find'.");
        }

        String query = userInput.substring(5).trim().toLowerCase();

        // Utilizing Java Streams to filter tasks
        List<Task> matchingTasks = taskList.getTasks().stream()
                .filter(task -> task.toString().toLowerCase().contains(query))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            ui.showNoTask();
        } else {
            ui.showTasks(matchingTasks);
        }
    }
}
