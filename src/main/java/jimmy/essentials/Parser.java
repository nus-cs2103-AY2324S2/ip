package jimmy.essentials;

import jimmy.exceptions.JimmyException;

/**
 * Represents a parser that parses the user input and task details.
 */
public class Parser {

    /**
     * Parses the user input into an array of strings.
     *
     * @param userInput The user input.
     * @return An array of strings containing the instruction and details.
     */
    public String parseUserInput(String userInput, Ui ui, Storage storage, TaskList tasks)
            throws IllegalArgumentException, JimmyException {
        assert userInput != null;
        assert tasks != null;
        String[] inputArray = userInput.trim().split(" ", 2);
        String instruction;
        String details = "";

        if (inputArray.length > 1) {
            instruction = inputArray[0];
            details = inputArray[1];
        } else {
            instruction = inputArray[0];
            details = "";
        }

        switch (instruction) {
        case "bye":
            storage.writeToFile(tasks.getTaskList());
            return ui.exit();
        case "list":
            return tasks.displayTasks();
        case "mark":
            return tasks.markTask(details);
        case "unmark":
            return tasks.unmarkTask(details);
        case "delete":
            return tasks.deleteTask(details);
        case "find":
            return tasks.findTask(details);
        default:
            return tasks.createNewTask(instruction, details);
        }
    }

    /**
     * Parses the deadline details into an array of strings.
     *
     * @param details The details of the deadline task.
     * @return An array of strings containing the deadline name and deadline.
     * @throws JimmyException If the deadline details are invalid.
     */
    public String[] parseDeadlineDetails(String details) throws JimmyException {
        if (!details.contains(" /by ")) {
            throw new JimmyException("Sorry! "
                    + "Please use the correct format when creating a new event.");
        }
        return details.split(" /by ", 2);
    }

    /**
     * Parses the event details into an array of strings.
     *
     * @param details The details of the event task.
     * @return An array of strings containing the event name, start time and end time.
     * @throws JimmyException If the event details are invalid.
     */
    public String[] parseEventDetails(String details) throws JimmyException {
        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new JimmyException("Sorry! "
                    + "Please use the correct format when creating a new event.");
        }
        return details.split(" /from | /to ");
    }
}
