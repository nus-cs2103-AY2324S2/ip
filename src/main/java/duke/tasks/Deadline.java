package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exceptions.TaskCreationException;

import duke.utils.Parser;

/**
 * This class implements the Deadline task type for the bot.
 * 
 * @author delishad21
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Basic constructor for Deadline.
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
     * Factory method that takes in user input and parses it to return a Deadline task.
     * 
     * @param isDone Marks if task is completed.
     * @param input User input to be parsed.
     * @return Deadline object.
     * @throws TaskCreationException
     * @throws DateTimeParseException
     */
    public static Deadline deadlineParse(boolean isDone, String input) 
    throws TaskCreationException, DateTimeParseException {
        if (!input.contains("/by")) {
            throw new TaskCreationException("Missing parameters: /by");
        }

        String description = input.substring(8, input.indexOf("/by")).trim();
        String deadlineString = input.substring(input.indexOf("/by") + 3).trim();
        // Check if inputs are blank
        String missingInfo = "";

        if (description.equals("")) {
            missingInfo = missingInfo + "\"description\" ";
        }
        if (deadlineString.equals("")) {
            missingInfo = missingInfo + "\"by\" ";
        }
        if (!missingInfo.equals("")) {
            throw new TaskCreationException("Missing information: " +  missingInfo);
        }

        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineString, Parser.INPUT_DT_FORMATTER);
        Deadline d = new Deadline(isDone, description, deadlineDateTime);
        return d;
    }

    
    /** 
     * Method for printing Deadline as a viewable String.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(Parser.OUTPUT_DT_FORMATTER) + ")";
    }

    
    /** 
     * Method for converting Deadline into a String for saving in save file.
     * 
     * @return String
     */
    @Override
    public String toSave() {
        return "[D]|" + super.toSave() + "|" + deadline.format(Parser.INPUT_DT_FORMATTER);
    }
}
