package anxi.handlers;

import anxi.command.AnxiException;
import anxi.command.TaskList;
import anxi.command.Ui;

/**
 * Handles inputs related to find tasks.
 */
public class FindHandler {

    /**
     * FindHandler constructor.
     */
    public FindHandler() {
    }

    /**
     * Finds tasks that partially/fully contain input string.
     *
     * @param find         String to find.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String   List of string of tasks that match or partially match find condition.
     */
    public String findTask(String find, TaskList taskList, Ui ui) {
        try {
            return find(find, taskList, ui);
        } catch (AnxiException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }
    }

    /**
     * Parses and calls relevant methods to find task that matches input.
     *
     * @param find         String to find.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String           List of string of tasks that match or partially match find condition.
     * @throws AnxiException    Thrown if there are missing or invalid inputs.
     */
    private String find(String find, TaskList taskList, Ui ui) throws AnxiException {
        if (find.matches("")) {
            throw new AnxiException("Invalid input, missing search term");
        }

        String result = taskList.findMatchingTasks(find.strip());

        if (result.isEmpty()) {
            return "No tasks matching your request";
        }
        return ui.printFindTask(result);
    }
}
