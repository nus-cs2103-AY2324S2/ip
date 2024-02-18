package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.KewgyException;

/**
 * Deadline task, with description and time to finish task by
 * 
 * @author Tang Yetong
 **/
public class Deadline extends Task {
    /**
     * Constructor for Deadlines
     * 
     * @param description description of task, with deadline included for parsing
     * @throws KewgyException
     */
    public Deadline(String description) throws KewgyException {
        super(description);

        String desc = super.description;
        
        if (!desc.contains("/by")) {
            throw new KewgyException("Date missing in description!");
        }

        String[] descParsed = desc.split("/by");

        if (descParsed.length == 1) {
            throw new KewgyException("Invalid date!");
        }

        super.description = descParsed[0].trim();
        updateTime(descParsed[1].trim().split(" "));
    }

     /**
     * Method to update time for tasks.
     * 1 times given for deadline, which are processed here.
     * 
     * @param times 1 time expected here
     * @throws KewgyException  if there's an issue with the time format
     */
    @Override
    public void updateTime(String... times) throws KewgyException {
        if (times.length != 1) {
            throw new KewgyException("Invalid times given for Deadline");
        }

        try {
            LocalDate date = LocalDate.parse(times[0].trim());
            String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            super.time1 = formattedDate;
        } catch (Exception e) {
            super.time1 = times[0];
        }
    }

    @Override
    public String toString() {
        return "[" + TaskType.D + "][" + super.getStatusIcon() + "] " + 
            super.description + " (by: " + time1 + ")";
    }
}
