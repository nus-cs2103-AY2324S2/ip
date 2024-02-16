package grizzly.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;
import java.util.stream.Stream;

import grizzly.exceptions.MissingInformationException;
import grizzly.exceptions.MissingParameterException;
import grizzly.utils.Parser;

/**
 * This class implements the Event task type for the bot.
 *
 * @author delishad21
 */
public class Event extends Task {
    private static final String[] REQUIRED_PARAMS = {"description", "from", "to"};
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
     * Returns a event object by taking in user input and parsing it.
     *
     * @param isDone Marks if task is completed.
     * @param params Hashtable of parameters for the generation of task.
     * @throws MissingInformationException Missing parameter information
     * @throws MissingParameterException Missing parameters
     * @throws DateTimeParseException Error parsing datetime passed as input.
     */
    public static Event eventParse(boolean isDone, Hashtable<String, String> params)
            throws MissingInformationException, MissingParameterException, DateTimeParseException {

        Parser.checkParams(params, REQUIRED_PARAMS);

        String[] filteredParams = Stream.of(REQUIRED_PARAMS).map(x -> params.get(x)).toArray(String[]::new);

        String description = filteredParams[0];
        LocalDateTime startDateTime = LocalDateTime.parse(filteredParams[1], Parser.INPUT_DT_FORMATTER);
        LocalDateTime endDateTime = LocalDateTime.parse(filteredParams[2], Parser.INPUT_DT_FORMATTER);

        return new Event(isDone, description, startDateTime, endDateTime);

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
