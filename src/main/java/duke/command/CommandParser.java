package duke.command;

import java.util.Arrays;

import duke.commons.exceptions.DukeException;

public class CommandParser {

    public static CommandType parseCommand(String userInput) throws DukeException {
        String[] splitInput = userInput.split("\\s+", 2);
        String command = splitInput[0];
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid Command: " + command);
        }
    }

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

    public static String parseToDo(String userInput) throws DukeException {
        String[] splitInput = userInput.split("\\s+", 2);
        try {
            String description = splitInput[1];
            return description;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Valid format: todo [description]");
        }
    }

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
