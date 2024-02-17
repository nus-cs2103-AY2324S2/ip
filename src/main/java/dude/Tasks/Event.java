package dude.Tasks;

import dude.Commands.EventCommand;
import dude.Exceptions.InvalidArgumentException;
import dude.Exceptions.InvalidDescriptionException;
import dude.Exceptions.InvalidFormatException;
import dude.Utils.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final LocalDateTime from_time;
    private final LocalDateTime to_time;


    public static Event from(String s) throws InvalidArgumentException, InvalidFormatException, InvalidDescriptionException {
        //Expects a string in the format "event <description> /at <from_time> to <to_time>"

        //get rid of the command
        // <description> </from> time </to> time
        String rest = utils.discardFirstWord(s.trim()).trim();

        String[] arr = rest.split(" ");

        int from_occurences = utils.countOccurrences(arr, "/from");

        if (from_occurences == 0 || from_occurences > 1){
            throw new InvalidFormatException("Invalid format. Follow this format :" + EventCommand.COMMAND_FORMAT + ". Provide one and only one '/from'.");
        }

        int to_occurences = utils.countOccurrences(arr, "/to");

        if (to_occurences == 0 || to_occurences > 1){
            throw new InvalidFormatException("Invalid format. Follow this format: " + EventCommand.COMMAND_FORMAT + ". Provide one and only one '/to'.");
        }

        //they will not be -1 as I have already checked for their occurences
        int from_index = utils.findIndex(arr, "/from");
        int to_index = utils.findIndex(arr, "/to");

        if (from_index > to_index){
            throw new InvalidFormatException("The 'from time' of an event cannot be after the 'to time'.");
        }

        //description is from 0 to from_index
        String description = "";
        for (int i = 0; i < from_index; i++){
            description += arr[i] + " ";
        }
        description = description.trim();
        if (description.isEmpty()){
            throw new InvalidDescriptionException("The description of an event cannot be empty.");
        }

        String from_time = "";
        for (int i = from_index+1; i < to_index; i++){
            from_time += arr[i] + " ";
        }
        from_time = from_time.trim();
        if (from_time.isEmpty()){
            throw new InvalidArgumentException("The 'from_time' of an event cannot be empty.");
        }

        String to_time = "";
        for (int i = to_index+1; i < arr.length; i++){
            to_time += arr[i] + " ";
        }
        to_time = to_time.trim();
        if (to_time.isEmpty()){
            throw new InvalidArgumentException("The 'to_time' of an event cannot be empty.");
        }

        try {
            LocalDateTime from = parseDate(from_time);
            LocalDateTime to = parseDate(to_time);
            return new Event(description, from, to);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Invalid date format after '/from' or '/to'. Use d/M/yyyy or d/M/yyy H:m in 24-hour format");
        }
    }

    public Event(String description, LocalDateTime from_time, LocalDateTime to_time) {
        super(description);
        this.from_time = from_time;
        this.to_time = to_time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(from_time) + " to: " + formatDate(to_time) + ")";
    }

    public LocalDateTime getFromTime() {
        return from_time;
    }

    public LocalDateTime getToTime() {
        return to_time;
    }
}
