package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * The `FindCommand` class represents a command in a task management application
 * that allows users to search for tasks based on a provided keyword or query.
 * It extends the abstract `Command` class.
 */
public class FindCommand extends Command {

    /**
     * Executes the "find" command to search for tasks in the provided `TaskList`
     * based on the user's input query. It searches for tasks that contain the
     * query in their `toString()` representation.
     *
     * @param taskList   The `TaskList` object containing the list of tasks to search through.
     * @param ui         The `Ui` object used to display messages to the user.
     * @param userInput  The user's input command, including the "find" keyword and search query.
     * @throws DukeException If there is an error during command execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {

        // Split user input to separate the command keyword from the search query
        String[] parts = userInput.split("\\s+", 2);
        String findTask = parts[1].toLowerCase();

        // Get the list of tasks from the TaskList
        List<Task> tasks = taskList.getTasks();

        // Initialize a list to store matching tasks
        List<Task> filteredList = new ArrayList<>();

        // Check if the task list is empty
        if (tasks.isEmpty()) {
            ui.showNoTask();
        }

        // Iterate through the tasks and add matching tasks to the filtered list
        for (Task task : tasks) {
            if (task.toString().contains(findTask)) {
                filteredList.add(task);
            }
        }

        // Display the matching tasks or a message if none are found
        if (filteredList.isEmpty()) {
            ui.showNoTask();
        } else {
            ui.showTasks(filteredList);
        }
    }
}
