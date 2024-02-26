package dude.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import dude.Commands.EventCommand;
import dude.Exceptions.InvalidArgumentException;
import dude.Exceptions.InvalidDescriptionException;
import dude.Exceptions.InvalidFormatException;
import dude.Utils.utils;


/**
 * The Event class represents a task with a description, start and end time.
 */
public class Event extends Task {

    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    /**
     * Constructor for the Event class.
     *
     * @param description The description of the event.
     * @param fromTime   The start time of the event.
     * @param toTime     The end time of the event.
     */
    public Event(String description, LocalDateTime fromTime, LocalDateTime toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Static method to create an Event object from parsing a string.
     * Expects a string in the format "event <description> /at <fromTime> to <toTime>"
     *
     * @param input The string to be parsed into an Event object.
     * @return The Event object created from the string.
     * @throws InvalidArgumentException If the 'from_time' or 'to_time' of the event is empty.
     * @throws InvalidFormatException If the format of the string is invalid.
     * @throws InvalidDescriptionException If the description of the event is empty.
     */
    public static Event from(String input) throws InvalidArgumentException,
            InvalidFormatException, InvalidDescriptionException {

        String rest = utils.discardFirstWord(input.trim()).trim();
        String[] arr = rest.split(" ");

        int fromOccurences = utils.countOccurrences(arr, "/from");

        if (fromOccurences == 0 || fromOccurences > 1) {
            throw new InvalidFormatException("Invalid format. Follow this format :" + EventCommand.COMMAND_FORMAT
                    + ". Provide one and only one '/from'.");
        }

        int toOccurrences = utils.countOccurrences(arr, "/to");

        if (toOccurrences == 0 || toOccurrences > 1) {
            throw new InvalidFormatException("Invalid format. Follow this format: " + EventCommand.COMMAND_FORMAT
                    + ". Provide one and only one '/to'.");
        }

        //they will not be -1 as I have already checked for their occurences
        int fromIndex = utils.findIndex(arr, "/from");
        int toIndex = utils.findIndex(arr, "/to");

        if (fromIndex > toIndex) {
            throw new InvalidFormatException("The 'from time' of an event cannot be after the 'to time'.");
        }

        //description is from 0 to fromIndex
        String description = "";
        for (int i = 0; i < fromIndex; i++) {
            description += arr[i] + " ";
        }
        description = description.trim();
        if (description.isEmpty()) {
            throw new InvalidDescriptionException("The description of an event cannot be empty.");
        }

        String fromTime = "";
        for (int i = fromIndex + 1; i < toIndex; i++) {
            fromTime += arr[i] + " ";
        }
        fromTime = fromTime.trim();
        if (fromTime.isEmpty()) {
            throw new InvalidArgumentException("The 'fromTime' of an event cannot be empty.");
        }

        String toTime = "";
        for (int i = toIndex + 1; i < arr.length; i++) {
            toTime += arr[i] + " ";
        }
        toTime = toTime.trim();
        if (toTime.isEmpty()) {
            throw new InvalidArgumentException("The 'toTime' of an event cannot be empty.");
        }

        try {
            LocalDateTime from = parseDate(fromTime);
            LocalDateTime to = parseDate(toTime);
            return new Event(description, from, to);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Invalid date format after '/from' or '/to'."
                    + "Use d/M/yyyy or d/M/yyy H:m in 24-hour format");
        }
    }


    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(fromTime) + " to: " + formatDate(toTime) + ")";
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start date-time  of the event.
     */
    public LocalDateTime getFromTime() {
        return fromTime;
    }

    /**
     * Returns the end date-time  of the event.
     *
     * @return The end date-time of the event.
     */
    public LocalDateTime getToTime() {
        return toTime;
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
