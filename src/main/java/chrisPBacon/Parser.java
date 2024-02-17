package chrisPBacon;

import exceptions.InvalidTaskNameException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  This class makes sense of the user's inputs.
 */
public class Parser {
    /**
     * Constructor for Parser.
     */
    public Parser() { }

    /**
     * Parses user input into todo task description.
     *
     * @return task description
     */
    public String parseTodo(String userInput) throws InvalidTaskNameException {
        if (userInput.length() < 6) {
            // If user did not input task name.
            throw new InvalidTaskNameException("Ooink oink! What's the name of your task?\n"
                    + " >> todo ...\n");
        }
        return userInput.substring(5);
    }

    /**
     * Parses user input into task name for deadline tasks.
     *
     * @return task name
     */
    public String parseDeadlineName(String userInput) throws InvalidTaskNameException {
        int len = userInput.length();
        int idx = userInput.indexOf("/by");
        boolean isWrongInput = len < 10 || idx < 0 || len < idx + 4;
        if (isWrongInput) {
            // If user did not input correct task description.
            throw new InvalidTaskNameException("Ooink oink! Please describe your deadline >.<\n"
                    + " >> deadline ... /by dd/MM/yyyy\n");
        }

        return userInput.substring(9, idx - 1);
    }

    /**
     * Parses user input into a LocalDate for deadline tasks.
     *
     * @return task deadline
     */
    public LocalDate parseDeadlineDate(String userInput) throws DateTimeParseException {
        int idx = userInput.indexOf("/by");
        String date = userInput.substring(idx + 4);
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }

    /**
     * Parses user input into task name for event tasks.
     *
     * @return task name
     */
    public String[] parseEvent(String userInput) throws InvalidTaskNameException {
        // Name, event start, event end.
        String[] description = new String[3];
        int len = userInput.length();
        int fromIdx = userInput.indexOf("/from");
        int toIdx = userInput.indexOf("/to");
        boolean isWrongInput = len < 7 || fromIdx < 0 || toIdx < 0
                || len < fromIdx + 6 || len < toIdx + 4;
        if (isWrongInput) {
            // If user did not input task description.
            throw new InvalidTaskNameException("Ooink oink! Please describe your event >.<\n"
                    + " >> event ... /from ... /to ...\n");
        }

        description[0] = userInput.substring(6, fromIdx - 1);
        description[1] = userInput.substring(fromIdx + 6, toIdx - 1);
        description[2] = userInput.substring(toIdx + 4);
        return description;
    }
}
