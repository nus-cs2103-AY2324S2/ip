package dude.Tasks;

import dude.Commands.EventCommand;
import dude.Exceptions.InvalidArgumentException;
import dude.Exceptions.InvalidDescriptionException;
import dude.Exceptions.InvalidFormatException;
import dude.Utils.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * The Event class represents a task with a description, start and end time.
 */
public class Event extends Task {

    private final LocalDateTime from_time;
    private final LocalDateTime to_time;

    /**
     * Constructor for the Event class.
     *
     * @param description The description of the event.
     * @param from_time   The start time of the event.
     * @param to_time     The end time of the event.
     */
    public Event(String description, LocalDateTime from_time, LocalDateTime to_time) {
        super(description);
        this.from_time = from_time;
        this.to_time = to_time;
    }

    /**
     * Static method to create an Event object from parsing a string.
     *
     * @param s The string to be parsed into an Event object.
     * @return The Event object created from the string.
     * @throws InvalidArgumentException If the 'from_time' or 'to_time' of the event is empty.
     * @throws InvalidFormatException If the format of the string is invalid.
     * @throws InvalidDescriptionException If the description of the event is empty.
     */
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


    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(from_time) + " to: " + formatDate(to_time) + ")";
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start date-time  of the event.
     */
    public LocalDateTime getFromTime() {
        return from_time;
    }

    /**
     * Returns the end date-time  of the event.
     *
     * @return The end date-time of the event.
     */
    public LocalDateTime getToTime() {
        return to_time;
    }

    /**
     * Returns whether the object is equal to this object.
     *
     * @param object The object to be compared.
     * @return Whether the object is equal to this object.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Event) {
            Event t = (Event) object;

            boolean isDescriptionEqual = t.getDescription().equals(this.getDescription());
            boolean isFromTimeEqual = t.getFromTime().equals(this.getFromTime());
            boolean isToTimeEqual = t.getToTime().equals(this.getToTime());

            return isDescriptionEqual && isFromTimeEqual && isToTimeEqual;
        }
        return false;
    }
}
