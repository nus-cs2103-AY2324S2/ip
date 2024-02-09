package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;
import java.util.stream.Stream;

import duke.exceptions.MissingInformationException;
import duke.exceptions.MissingParameterException;
import duke.utils.Parser;

/**
 * This class implements the Deadline task type for the bot.
 *
 * @author delishad21
 */
public class Deadline extends Task {

    private static String REQUIRED_PARAMS[] = {"description", "by"};

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
     * Returns a Deadline object by taking in user input and parsing it.
     *
     * @param isDone Marks if task is completed.
     * @param input User input to be parsed.
     * @return Deadline object.
     * @throws TaskCreationException
     * @throws DateTimeParseException
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
