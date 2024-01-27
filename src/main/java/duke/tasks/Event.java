package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exceptions.TaskCreationException;
import duke.utils.Parser;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(boolean isDone, String description, LocalDateTime start, LocalDateTime end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    public static Event eventParse(boolean isDone, String input)  throws TaskCreationException, DateTimeParseException {
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

        LocalDateTime startDateTime = LocalDateTime.parse(startString, Parser.inputdtFormatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endString, Parser.inputdtFormatter);
            
        Event e = new Event(isDone, description, startDateTime, endDateTime);
        return e;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(Parser.outputdtFormatter) + " to: " + end.format(Parser.outputdtFormatter) + ")";
    }

    @Override
    public String toSave() {
        return "[E]|" + super.toSave() + "|" + start.format(Parser.inputdtFormatter) + "|" + end.format(Parser.inputdtFormatter);
    }
}
