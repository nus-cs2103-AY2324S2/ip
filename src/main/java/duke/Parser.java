package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that handles users input and
 * returns the respective commands and descriptions.
 */
public class Parser {
    private String input;
    private String commandWord;

    /**
     * Initializes a Parser with the input from the user.
     *
     * @param input the input from the user
     */
    public Parser(String input) {
        this.input = input;
    }

    /**
     * Returns the command word from the user input.
     *
     * @return command word trimmed from user input
     * @throws DukeException if the command word is empty
     */
    public String getCommandWord() throws DukeException {
        if (input.trim().isEmpty()) {
            throw new DukeException("Command cannot be empty.");
        }
        this.commandWord = input.split(" ")[0].trim();
        assert commandWord != null : "Command word should not be null";
        return commandWord;
    }

    /**
     * Returns the description for the new task from user input.
     *
     * @return description of the task
     * @throws DukeException if the description is empty
     */
    public String getDescription() throws DukeException {
        formatCheck("todo");
        if (input.contains("/")) {
            return input.split(" ", 2)[1].split("/")[0].trim();
        }
        return input.split(" ", 2)[1];
    }

    /**
     * Returns the date for the deadline task from user input.
     *
     * @return date in LocalDate format
     * @throws DukeException if the date is empty
     * @throws DateTimeParseException if the date is not in the correct format
     */
    public LocalDateTime getBy() throws DukeException, DateTimeParseException {
        formatCheck("deadline");

        String dateTimeString = input.split("/by ")[1].trim();
        return Task.getLocalDateTimeInput(dateTimeString);
    }

    /**
     * Returns the date in array form for the event task from user input.
     * The first element is the start date and the second element is the end date.
     *
     * @return array of dates in LocalDate format
     * @throws DukeException if the date is empty
     * @throws DateTimeParseException if the date is not in the correct format
     */
    public LocalDate[] getFromTo() throws DukeException, DateTimeParseException {
        formatCheck("event");
        LocalDate from = Task.getLocalDateInput(input.split("/from")[1].split("/to")[0].trim());
        LocalDate to = Task.getLocalDateInput(input.split("/to")[1].trim());
        return new LocalDate[] {from, to};
    }

    private String deadlineFormatError() throws DukeException {
        return "Please use the following format: deadline <description> /by <dd-mm-yyyy> <HHmm> (time optional)";
    }

    private void formatCheck(String taskType) throws DukeException {
        String formatStringInfo = "Please use the following format:" + taskType + " <description>";

        switch (taskType) {
        case "todo":
            if (input.split(" ", 2).length == 1) {
                throw new DukeException("The description of a task cannot be empty. \n\t"
                    + formatStringInfo);
            }
            break;
        case "deadline":
            if (input.split(" ", 2).length == 1 || input.split("/by ").length != 2) {
                throw new DukeException("The deadline and description for a task cannot be empty. \n\t"
                    + deadlineFormatError());
            } else if (!input.contains("/by")) {
                throw new DukeException("Invalid command for deadline. \n\t"
                    + deadlineFormatError());
            }
            break;
        case "event":
            if (input.split(" ", 2).length == 1) {
                throw new DukeException("The date and description for an event cannot be empty. \n\t"
                    + formatStringInfo + " /from <dd-mm-yyyy> /to <dd-mm-yyyy>");
            } else if (!input.contains("/from") || !input.contains("/to") || input.split("/from ").length > 2
                || input.split("/to ").length > 2) {
                throw new DukeException("Invalid command for event. \n\t"
                    + formatStringInfo + " /from <dd-mm-yyyy> /to <dd-mm-yyyy>");
            }
            break;
        default:
            assert taskType != null || !taskType.isEmpty() : "Task type should be null or empty";
            throw new DukeException("Invalid command. \n\t"
                + formatStringInfo);
        }
    }

    /**
     * Returns the index of the task to be modified from user input.
     *
     * @return index of the task
     * @throws DukeException if the index is empty
     */
    public int getIndex() throws DukeException {
        if (input.split(" ").length == 1) {
            throw new DukeException("The index of a task cannot be empty.");
        }
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }


}
