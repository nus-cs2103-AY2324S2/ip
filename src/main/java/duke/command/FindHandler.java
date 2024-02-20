package duke.command;

/**
 * Handle inputs related to find tasks.
 */
public class FindHandler {

    /**
     * FindHandler constructor.
     */
    public FindHandler() {
    }

    /**
     * Find tasks that partially/fully contain input string.
     *
     * @param input         String to find.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String   List of string of tasks that match or partially match find condition.
     */
    public String findTask(String input, TaskList taskList, Ui ui) {
        try {
            return find(input, taskList, ui);
        } catch (DukeException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }
    }

    /**
     * Parse and call relevant methods to find task that matches input.
     *
     * @param input         String to find.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String           List of string of tasks that match or partially match find condition.
     * @throws DukeException    Thrown if there are missing or invalid inputs.
     */
    private String find(String input, TaskList taskList, Ui ui) throws DukeException {
        if (input.matches("")) {
            throw new DukeException("Invalid input, missing search term");
        }

        return ui.printFindTask(taskList.findMatchingTasks(input.strip()));
    }
}
