package bartenderbob;

/**
 * Represents a parser that receives and handle user inputs.
 */
public class InputHandler {
    /**
     * Handles the user input and returns the appropriate response.
     * @param taskList The TaskList object that stores the tasks.
     * @param ui The UI object that handles the user interface.
     * @param userInput The user input.
     * @return The response to the user input.
     */
    public static String handleInput(TaskList taskList, Ui ui, String userInput) {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "ui cannot be null";
        assert userInput != null : "user input cannot be null";
        String[] stringComponents = userInput.split(" ");
        String firstWord = stringComponents[0];
        if (firstWord.equals("bye")) {
            return ui.leave();
        } else {
            try {
                return processCommand(firstWord, stringComponents, userInput, taskList);
            } catch (BartenderBobException e) {
                //This exception is when the index from user input is out of the TASKS bounds.
                return e.tasksOutOfBounds();
            } catch (IndexOutOfBoundsException e) {
                //IndexOutOfBoundsException is for missing userInputs.
                BartenderBobException error = new BartenderBobException(firstWord);
                return error.displayError();
            } catch (IllegalArgumentException e) {
                return ui.showInvalidDateFormat();
            }
        }
    }

    /**
     * Processes the user input and returns the appropriate response.
     * @param firstWord The first word of the user input.
     * @param stringComponents The components of the user input.
     * @param userInput The user input.
     * @param taskList The TaskList object that stores the tasks.
     * @return The response to the user input.
     * @throws BartenderBobException If the user input is invalid.
     */
    public static String processCommand(String firstWord,
                                        String[] stringComponents,
                                        String userInput, TaskList taskList) throws BartenderBobException {
        switch (firstWord) {
        case "list":
            return processListCommand(taskList);
        case "mark":
            return processMarkCommand(stringComponents, taskList);
        case "unmark":
            return processUnmarkCommand(stringComponents, taskList);
        case "delete":
            return processDeleteCommand(stringComponents, taskList);
        case "find":
            return processFindCommand(stringComponents, taskList);
        case "todo":
            return processTodoCommand(userInput, taskList);
        case "deadline":
            return processDeadlineCommand(userInput, taskList);
        case "event":
            return processEventCommand(userInput, taskList);
        default:
            return BartenderBobException.invalidInput(userInput);
        }
    }

    /**
     * Processes the 'list' command and returns the appropriate response.
     * @param taskList The TaskList object that stores the tasks.
     * @return The response to the 'list' command.
     */
    private static String processListCommand(TaskList taskList) {
        return taskList.list();
    }

    /**
     * Processes the 'mark' command and returns the appropriate response.
     * @param stringComponents The components of the user input.
     * @param taskList The TaskList object that stores the tasks.
     * @return The response to the 'mark' command.
     * @throws BartenderBobException If the user input is invalid.
     */
    private static String processMarkCommand(String[] stringComponents, TaskList taskList)
            throws BartenderBobException {
        String index = stringComponents[1];
        return taskList.markDone(index);
    }

    /**
     * Processes the 'unmark' command and returns the appropriate response.
     * @param stringComponents The components of the user input.
     * @param taskList The TaskList object that stores the tasks.
     * @return The response to the 'unmark' command.
     * @throws BartenderBobException If the user input is invalid.
     */
    private static String processUnmarkCommand(String[] stringComponents, TaskList taskList)
            throws BartenderBobException {
        String index = stringComponents[1];
        return taskList.unmarkDone(index);
    }

    /**
     * Processes the 'delete' command and returns the appropriate response.
     * @param stringComponents The components of the user input.
     * @param taskList The TaskList object that stores the tasks.
     * @return The response to the 'delete' command.
     * @throws BartenderBobException If the user input is invalid.
     */
    private static String processDeleteCommand(String[] stringComponents, TaskList taskList)
            throws BartenderBobException {
        String index = stringComponents[1];
        return taskList.delete(index);
    }

    /**
     * Processes the 'find' command and returns the appropriate response.
     * @param stringComponents The components of the user input.
     * @param taskList The TaskList object that stores the tasks.
     * @return The response to the 'find' command.
     */
    private static String processFindCommand(String[] stringComponents, TaskList taskList) {
        String substring = stringComponents[1];
        return taskList.find(substring);
    }

    /**
     * Processes the 'todo' command and returns the appropriate response.
     * @param userInput The user input.
     * @param taskList The TaskList object that stores the tasks.
     * @return The response to the 'todo' command.
     */
    private static String processTodoCommand(String userInput, TaskList taskList) {
        String todoPattern = "todo ";
        String str = userInput.split(todoPattern)[1];
        ToDo task = new ToDo(str);
        return taskList.store(task);
    }
    /**
     * Processes the 'deadline' command and returns the appropriate response.
     * @param userInput The user input.
     * @param taskList The TaskList object that stores the tasks.
     * @return The response to the 'deadline' command.
     */
    private static String processDeadlineCommand(String userInput, TaskList taskList) {
        String deadlinePattern = "deadline | /by ";
        String[] deadlineComponents = userInput.split(deadlinePattern);
        Deadline deadline = new Deadline(deadlineComponents[1], deadlineComponents[2]);
        return taskList.store(deadline);
    }

    /**
     * Processes the 'event' command and returns the appropriate response.
     * @param userInput The user input.
     * @param taskList The TaskList object that stores the tasks.
     * @return The response to the 'event' command.
     */
    private static String processEventCommand(String userInput, TaskList taskList) {
        String eventPattern = "event | /from | /to ";
        String[] eventComponents = userInput.split(eventPattern);
        Event event = new Event(eventComponents[1], eventComponents[2], eventComponents[3]);
        return taskList.store(event);
    }
}
