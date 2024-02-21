package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.KewgyException;

/**
 * Event task, with description and time to start and finish task by
 * 
 * @author Tang Yetong
 **/
public class Event extends Task {
    /**
     * Constructor for Events
     * 
     * @param description description with 2 dates included for parsing
     * @throws KewgyException
     */
    public Event(String description) throws KewgyException {
        super(description);

        parseEventDescription(description);
    }

    /**
     * Parses the event description to extract dates and format them properly
     * 
     * @param description the original description
     * @return formatted event description
     * @throws KewgyException if there's an issue with the description format
     */
    private void parseEventDescription(String description) throws KewgyException {
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new KewgyException("Dates missing in description!");
        }

        String[] descParsed = description.split("/from");
        String[] dateParsed = descParsed[1].split("/to");

        if (dateParsed.length <= 1 || dateParsed[0].isBlank() || dateParsed[1].isBlank()) {
            throw new KewgyException("Invalid dates!");
        }

        super.description = descParsed[0].trim();
        updateTime(dateParsed);
    }

    /**
     * Method to update time for tasks.
     * 2 times given for events, which are processed here.
     * 
     * @param times 2 times expected here
     * @throws KewgyException  if there's an issue with the time format
     */
    @Override
    public void updateTime(String... times) throws KewgyException {
        if (times.length != 2) {
            throw new KewgyException("Invalid times given for Event");
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