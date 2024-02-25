package scribbles.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import scribbles.ui.Ui;

/**
 * This class deals with making sense of user commands.
 */
public class Parser {
    private String input;
    private Ui ui;

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructs a new instance of Parser with the specified input string.
     *
     * @param input input string to be parsed.
     */
    public Parser(String input) {
        this.input = input;
        ui = new Ui();
    }

    /**
     * Gets the first word of instruction, which is the main command for following actions.
     *
     * @return The first word of input
     */
    public String getCommand() {
        String[] inputParts = this.input.split("\\s+");
        return inputParts[0];
    }

    /**
     * Gets the index of task to perform command on in input.
     *
     * @return index of task to perform instruction on
     * @throws IndexOutOfBoundsException If user leaves out index from instructions
     */
    public int getIndex() throws IndexOutOfBoundsException {
        String[] inputParts = this.input.split("\\s+");
        String indexString = inputParts[1].trim();
        int index = Integer.parseInt(indexString);
        return index;
    }

    /**
     * Gets the description of the to-do task in input
     *
     * @return Description of task
     * @throws IndexOutOfBoundsException If user leaves the description blank
     */
    public String getTodoDescription() throws IndexOutOfBoundsException {
        String[] inputParts = this.input.split("\\s+", 2);
        String description = inputParts[1].trim();
        return description;
    }

    /**
     * Checks if there are missing information in deadline instructions.
     *
     * @return true if there are missing information.
     * @throws IndexOutOfBoundsException If date/time string is missing
     */
    public boolean isMissingDeadlineInformation() throws IndexOutOfBoundsException {
        if (!this.input.contains(" /by ")) {
            return true;
        }
        if ((this.input.split("\\s+", 2)[1].split("\\s+/by\\s+", 2)[0].trim().isEmpty())) {
            return true;
        }
        if ((this.input.split("\\s+", 2)[1].split("\\s+/by\\s+", 2)[1].isEmpty())) {
            return true;
        }
        return false;
    }

    /**
     * Gets the description of the deadline task.
     *
     * @return Description of deadline
     */
    public String getDeadlineDescription() {
        return this.input.split("\\s+", 2)[1].split("\\s+/by\\s+", 2)[0].trim();
    }

    /**
     * Gets the date/time of the deadline.
     *
     * @return Date and time of deadline
     * @throws IndexOutOfBoundsException If date/time are missing from instruction
     * @throws DateTimeParseException If date/time String are in the wrong format
     */
    public LocalDateTime getDeadlineBy() throws IndexOutOfBoundsException, DateTimeParseException {
        String dateTime = this.input.split("\\s+", 2)[1].split("\\s+/by\\s+")[1].trim();
        LocalDateTime by = LocalDateTime.parse(dateTime, DATE_TIME_FORMAT);
        return by;
    }

    /**
     * Checks if there are missing information in event instructions.
     *
     * @return true if there are missing information
     * @throws IndexOutOfBoundsException If date/time string is missing
     */
    public boolean isInvalidEvent() throws IndexOutOfBoundsException {
        if (!this.input.contains(" /from ") || !this.input.contains(" /to ")) {
            return true;
        }
        if (this.input.indexOf("/from") > this.input.indexOf("/to")) {
            return true;
        }
        if (this.input.split("\\s+", 2)[1].split("\\s+/from\\s+", 2)[0].trim().isEmpty()) {
            // no description
            return true;
        }
        if (this.input.split("\\s+/from\\s+", 2)[1].split("\\s+/to\\s+",
                2)[0].trim().isEmpty()) {
            // no start date and time
            return true;
        }
        if (this.input.split("\\s+/to\\s+", 2)[1].trim().isEmpty()) {
            // no end date and time
            return true;
        }
        return false;
    }

    /**
     * Gets event description
     *
     * @return The event description
     */
    public String getEventDescription() {
        return this.input.split("\\s+", 2)[1].split("\\s+/from\\s+", 2)[0].trim();
    }

    /**
     * Gets event start time as LocalDateTime.
     *
     * @return Start time of event.
     * @throws DateTimeParseException If date/time string is formatted wrongly.
     */
    public LocalDateTime getStartDateTime() throws DateTimeParseException {
        String startString = this.input.split("\\s+/from\\s+", 2)[1].split("\\s+/to\\s+",
                2)[0].trim();
        LocalDateTime start = LocalDateTime.parse(startString, DATE_TIME_FORMAT);
        return start;
    }

    /**
     * Gets event end time as LocalDateTime.
     *
     * @return End time of event.
     * @throws DateTimeParseException If date/time string is formatted wrongly.
     */
    public LocalDateTime getEndDateTime() throws IndexOutOfBoundsException, DateTimeException {
        String endString = this.input.split("\\s+/to\\s+", 2)[1].trim();
        LocalDateTime end = LocalDateTime.parse(endString, DATE_TIME_FORMAT);
        return end;
    }

    /**
     * Checks if the start of the event of the input is after the end of the event of the input.
     *
     * @param start start date/time of the event
     * @param end end date/time of the event
     * @return true if start is after the end
     */
    public boolean isInvalidStartAndEnd(LocalDateTime start, LocalDateTime end) {
        return start.isAfter(end);
    }

    /**
     * Gets the keyword that user wants to look for in tasks.
     *
     * @return The keyword.
     * @throws IndexOutOfBoundsException If find command does not contain a keyword.
     */
    public String getFindKeyword() throws IndexOutOfBoundsException {
        String keyword = this.input.split("\\s+", 2)[1].trim();
        return keyword;
    }

    /**
     * Gets the order in which the user wants to sort by.
     *
     * @return The order
     * @throws IndexOutOfBoundsException If the command does not specify any order
     */
    public String getSortingOrder() throws IndexOutOfBoundsException {
        String order = this.input.split("\\s+", 2)[1].trim();
        return order;
    }
}

