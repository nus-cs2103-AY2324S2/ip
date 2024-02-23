package duke.command;

import java.util.Arrays;

import duke.commons.exceptions.DukeException;

/**
 * Parses user input into commands and arguments.
 * Facilitates the interpretation of user input, converting strings into
 * actionable commands and parameters.
 * Includes methods for parsing different aspects of tasks such as indices and
 * descriptions, ensuring inputs are correctly formatted before processing.
 */
public class CommandParser {

    /**
     * Parses the user input to determine the command type.
     * 
     * @param userInput The complete user input string.
     * @return The CommandType corresponding to the user input.
     * @throws DukeException If the command is not recognized.
     */
    public static CommandType parseCommand(String userInput) throws DukeException {
        String[] splitInput = userInput.split("\\s+", 2);
        String command = splitInput[0];
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid Command: " + command);
        }
    }

    /**
     * Extracts the task index from the user input.
     * 
     * @param userInput The user input containing the task index.
     * @return The index of the task in the task list (0-based).
     * @throws DukeException If the task index is not provided, is not an integer,
     *                       or is out of bounds.
     */
    public static int parseTaskIndex(String userInput) throws DukeException {
        String[] splitInput = userInput.split("\\s+", 2);
        try {
            int idx = Integer.parseInt(splitInput[1]) - 1;
            if (idx < 0) {
                throw new DukeException("Invalid task Index" + splitInput[1]);
            }
            return idx;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please provide a task index");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid task index: " + splitInput[1]);
        }
    }

    /**
     * 
     * @param userInput
     * @return String[] Array of keywords to search for in task list
     * @throws DukeException If keywords not provided.
     */
    public static String[] parseFind(String userInput) throws DukeException {
        String[] splitInput = userInput.split("\\s+");
        try {
            String[] keywords = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            return keywords;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please provide keyword(s) to search for");
        } 
    }

    /**
     * Extracts the description for a ToDo task from the user input.
     * 
     * @param userInput The user input containing the description of the ToDo task.
     * @return The description of the ToDo task.
     * @throws DukeException If the description is not provided.
     */
    public static String parseToDo(String userInput) throws DukeException {
        String[] splitInput = userInput.split("\\s+", 2);
        try {
            String description = splitInput[1];
            return description;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Valid format: todo [description]");
        }
    }

    /**
     * Extracts the details (description and due date) for a Deadline task from the
     * user input.
     * 
     * @param userInput The user input containing the details of the Deadline task.
     * @return An array where the first element is the description and the second
     *         element is the due date of the Deadline task.
     * @throws DukeException If the input format is incorrect or if the details are
     *                       not provided.
     */
    public static String[] parseDeadline(String userInput) throws DukeException {
        String[] splitInput = userInput.split("\\s+", 2);
        try {
            String[] deadlineDetails = splitInput[1].split(" /by ", 2);
            if (deadlineDetails.length != 2) {
                throw new DukeException("Valid format: deadline [description] /by [duedate]");
            }
            return deadlineDetails;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Valid format: deadline [description] /by [duedate]");
        }
    }

    /**
     * Extracts the details (description, start date, and end date) for an Event
     * task from the user input.
     * 
     * @param userInput The user input containing the details of the Event task.
     * @return An array where the first element is the description, the second is
     *         the start date, and the third is the end date of the Event task.
     * @throws DukeException If the input format is incorrect or if the details are
     *                       not provided properly.
     */
    public static String[] parseEvent(String userInput) throws DukeException {
        String[] splitInput = userInput.split("\\s+", 2);
        try {
            String[] eventDetails = splitInput[1].split(" /from ", 2);
            String description = eventDetails[0];
            String[] timings = eventDetails[1].split(" /to ", 2);
            String start = timings[0];
            String end = timings[1];
            return new String[] { description, start, end };
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Valid format: event [description] /from [start] /to [end]");
        }
    }
}
