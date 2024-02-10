package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Exceptions.DukeException;

/**
 * Event task, with description and time to start and finish task by
 * 
 * @author Tang Yetong
 **/
public class Event extends Task {
    /**
     * Creation of event task
     * 
     * @param description description with 2 dates included for parsing
     * @throws DukeException
     */
    public Event(String description) throws DukeException {
        super(parseEventDescription(description));
    }

    /**
     * Parses the event description to extract dates and format them properly
     * 
     * @param description the original description
     * @return formatted event description
     * @throws DukeException if there's an issue with the description format
     */
    private static String parseEventDescription(String description) throws DukeException {
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new DukeException("Dates missing in description!");
        }

        String[] descParsed = description.split("/from");
        String[] dateParsed = descParsed[1].split("/to");

        if (dateParsed.length <= 1 || dateParsed[0].isBlank() || dateParsed[1].isBlank()) {
            throw new DukeException("Invalid dates!");
        }

        try {
            LocalDate date1 = LocalDate.parse(dateParsed[0].trim());
            LocalDate date2 = LocalDate.parse(dateParsed[1].trim());

            String formattedDate1 = formatDate(date1);
            String formattedDate2 = formatDate(date2);

            return descParsed[0].trim() + " (from: " + formattedDate1 + " to: " + formattedDate2 + ")";
        } catch (Exception e) {
            return descParsed[0].trim() + " (from: " + dateParsed[0].trim() + " to: " + dateParsed[1].trim() + ")";
        }
    }

    /**
     * Formats a LocalDate object into a string in the "MMM d yyyy" format
     * 
     * @param date the date to be formatted
     * @return the formatted date string
     */
    private static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[" + TaskType.E + "][" + super.getStatusIcon() + "] " + super.getDescription();
    }
}