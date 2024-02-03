package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exceptions.TaskCreationException;
import duke.utils.Parser;

/**
 * This class implements the Event task type for the bot.
 *
 * @author delishad21
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates Event object
     *
     * @param isDone Marks if task is completed.
     * @param description Description of the task.
     * @param start Datetime value marking the start of the event.
     * @param end Datetime value for marking the end of the event.
     */
    public Event(boolean isDone, String description, LocalDateTime start, LocalDateTime end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a deadline object by taking in user input and parsing it.
     *
     * @param isDone Marks if task is completed.
     * @param input User input to be parsed.
     * @return Event object.
     * @throws TaskCreationException
     * @throws DateTimeParseException
     */
    public static Event eventParse(boolean isDone, String input)
            throws TaskCreationException, DateTimeParseException {
        // Check missing parameters
        String missingParams = "";

        if (!input.contains("/from")) {
            missingParams = missingParams + "/from ";
        }
        if (!input.contains("/to")) {
            missingParams = missingParams + "/to ";
        }
        if (!missingParams.equals("")) {
            throw new TaskCreationException(" Missing parameters: " + missingParams);
        }

        // Check order of parameters
        if (input.indexOf("/from") > input.indexOf("/to")) {
            throw new TaskCreationException("Bad order of parameters, correct order should be: /from, /to");
        }

        String description = input.substring(5, input.indexOf("/from")).trim();

        String startString = input.substring(input.indexOf("/from") + 5, input.indexOf("/to")).trim();
        String endString = input.substring(input.indexOf("/to") + 3).trim();

        // Check if inputs are blank
        String missingInfo = "";

        if (description.equals("")) {
            missingInfo = missingInfo + "\"description\" ";
        }
        if (startString.equals("")) {
            missingInfo = missingInfo + "\"from\"  ";
        }
        if (endString.equals("")) {
            missingInfo = missingInfo + "\"to\" ";
        }

        if (!missingInfo.equals("")) {
            throw new TaskCreationException("Missing information: " + missingInfo);
        }

        LocalDateTime startDateTime = LocalDateTime.parse(startString, Parser.INPUT_DT_FORMATTER);
        LocalDateTime endDateTime = LocalDateTime.parse(endString, Parser.INPUT_DT_FORMATTER);

        Event e = new Event(isDone, description, startDateTime, endDateTime);
        return e;
    }

    /**
     * Returns Event as a viewable String.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(Parser.OUTPUT_DT_FORMATTER)
               + " to: " + end.format(Parser.OUTPUT_DT_FORMATTER) + ")";
    }

    /**
     * Converts Event into a String for saving in save file.
     *
     * @return String
     */
    @Override
    public String toSave() {
        return "[E]|" + super.toSave() + "|" + start.format(Parser.INPUT_DT_FORMATTER)
               + "|" + end.format(Parser.INPUT_DT_FORMATTER);
    }
}
