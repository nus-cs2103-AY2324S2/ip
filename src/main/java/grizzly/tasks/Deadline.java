package grizzly.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;
import java.util.stream.Stream;

import grizzly.exceptions.MissingInformationException;
import grizzly.exceptions.MissingParameterException;
import grizzly.utils.Parser;

/**
 * This class implements the Deadline task type for the bot.
 *
 * @author delishad21
 */
public class Deadline extends Task {

    private static final String[] REQUIRED_PARAMS = {"description", "by"};

    private LocalDateTime deadline;

    /**
     * Creates Deadline object.
     *
     * @param isDone Marks if task is completed.
     * @param description Description of the task.
     * @param deadline Datetime value for the deadline of the task.
     */
    public Deadline(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    /**
     * Returns a deadline object by taking in a Hashtable of parameters and parsing it.
     *
     * @param isDone Marks if task is completed.
     * @param params Hashtable of parameters for the generation of task.
     * @throws MissingInformationException Missing parameter information.
     * @throws MissingParameterException Missing parameters.
     * @throws DateTimeParseException Error parsing datetime passed as input.
     */
    public static Deadline deadlineParse(boolean isDone, Hashtable<String, String> params)
            throws MissingInformationException, MissingParameterException, DateTimeParseException {

        Parser.checkParams(params, REQUIRED_PARAMS);

        String[] filteredParams = Stream.of(REQUIRED_PARAMS).map(x -> params.get(x)).toArray(String[]::new);

        String description = filteredParams[0];
        LocalDateTime deadlineDateTime = LocalDateTime.parse(filteredParams[1], Parser.INPUT_DT_FORMATTER);

        return new Deadline(isDone, description, deadlineDateTime);
    }


    /**
     * Returns Deadline as a viewable String.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(Parser.OUTPUT_DT_FORMATTER) + ")";
    }


    /**
     * Coverts Deadline into a String for saving in save file.
     *
     * @return String
     */
    @Override
    public String toSave() {
        return "[D]|" + super.toSave() + "|" + deadline.format(Parser.INPUT_DT_FORMATTER);
    }
}
