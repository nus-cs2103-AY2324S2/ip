package duke.commands;
import duke.exceptions.DukeException;

/**
 * Represents an abstract command class.
 */
public abstract class Command {
    /**
     * Abstract method to perform action for a command.
     *
     * @return String Message with corresponding command.
     * @throws  DukeException throws an exception when error arise in execute.
     */
    public abstract String execute() throws DukeException;

    /**
     * Abstract method to check if command is exit command.
     *
     * @return boolean whether command is the exit command.
     */
    public abstract boolean isExit();

    /**
     * Get the task name of the task.
     *
     * @param commandWord The command word of the input.
     * @param input The user input.
     * @return String The name of the task.
     * @throws DukeException throws an exception when input or commandWord is not valid.
     */
    public static String getTaskName(String commandWord, String input) throws DukeException {
        if (commandWord.equalsIgnoreCase("todo")) {
            if (input.length() < 6) {
                throw new DukeException("Please enter a task name for Duke.Tasks.Todo!! >.<");
            }
            String taskName = input.substring(5);
            return taskName;
        } else if (commandWord.equalsIgnoreCase("deadline")) {
            int endIndex = input.indexOf("/by");
            if (input.length() < 10 || endIndex == -1) {
                throw new DukeException("Please enter a task name for Duke.Tasks.Deadline!! >.<");
            }
            String taskName = input.substring(9, endIndex);
            return taskName;
        } else if (commandWord.equalsIgnoreCase("event")) {
            int endIndex = input.indexOf("/from");
            if (input.length() < 7 || endIndex == -1) {
                throw new DukeException("Please enter a task name for Duke.Tasks.Event!! >.<");
            }
            String taskName = input.substring(6, endIndex);
            return taskName;
        } else {
            throw new DukeException("Please use one of the 3 tasks!! >.<");
        }
    }

    /**
     * Get the starting date of the task.
     *
     * @param input The user input.
     * @return String The start date of the task.
     * @throws DukeException throws an exception when input is not valid.
     */
    public static String getStartDate(String input) throws DukeException {
        int startIndex = input.indexOf("/from") + 6;
        int endIndex = input.indexOf("/to");

        if (startIndex == input.length() || startIndex == 5) {
            throw new DukeException("Please use the keyword /from for your event! >.<");
        } else if (startIndex > input.length()) {
            throw new DukeException("Please enter a start date for your event! >.<");
        } else if (endIndex == -1) {
            throw new DukeException("Please use the keyword /to for your event! >.<");
        } else if (startIndex > endIndex) {
            throw new DukeException("Please enter an end date for your event! >.<");
        }
        return input.substring(startIndex, endIndex);
    }

    /**
     * Get the ending date of the task.
     *
     * @param input The user input.
     * @return String The end date of the task.
     * @throws DukeException throws an exception when input is not valid.
     */
    public static String getEndDate(String task, String input) throws DukeException {
        String startWord = task.equalsIgnoreCase("deadline") ? "/by" : "/to";
        int startIndex = input.indexOf(startWord) + startWord.length() + 1;

        if (startIndex > input.length()) {
            throw new DukeException(String.format("Please enter an end date for your %s!", task));
        }
        return input.substring(startIndex);
    }
}
