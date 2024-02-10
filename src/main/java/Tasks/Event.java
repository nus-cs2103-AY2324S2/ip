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
        super(description);

        parseEventDescription(description);
    }

    /**
     * Parses the event description to extract dates and format them properly
     * 
     * @param description the original description
     * @return formatted event description
     * @throws DukeException if there's an issue with the description format
     */
    private void parseEventDescription(String description) throws DukeException {
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new DukeException("Dates missing in description!");
        }

        String[] descParsed = description.split("/from");
        String[] dateParsed = descParsed[1].split("/to");

        if (dateParsed.length <= 1 || dateParsed[0].isBlank() || dateParsed[1].isBlank()) {
            throw new DukeException("Invalid dates!");
        }

        super.description = descParsed[0].trim();
        updateTime(dateParsed);
    }

    @Override
    public void updateTime(String... times) throws DukeException {
        if (times.length != 2) {
            throw new DukeException("Invalid times given for Event");
        }

        try {
            LocalDate date1 = LocalDate.parse(times[0].trim());
            LocalDate date2 = LocalDate.parse(times[1].trim());

            String formattedDate1 = formatDate(date1);
            String formattedDate2 = formatDate(date2);

            super.time1 = formattedDate1;
            super.time2 = formattedDate2;
        } catch (Exception e) {
            super.time1 = times[0].trim();
            super.time2 = times[1].trim();
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
        return "[" + TaskType.E + "][" + super.getStatusIcon() + "] " + 
            super.getDescription() + " (from: " + super.time1 + " to: " + super.time2 + ")";
    }
}