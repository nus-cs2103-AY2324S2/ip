package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Exceptions.DukeException;

/**
 * Deadline task, with description and time to finish task by
 * 
 * @author Tang Yetong
 **/
public class Deadline extends Task {
    /**
     * Create deadline task
     * 
     * @param description description of task, with deadline included for parsing
     * @throws DukeException
     */
    public Deadline(String description) throws DukeException {
        super(description);

        String desc = super.description;
        if (!desc.contains("/by")) throw new DukeException("Date missing in description!");

        String[] descParsed = desc.split("/by");

        if (descParsed.length == 1) throw new DukeException("Invalid date!");

        try {
            LocalDate date = LocalDate.parse(descParsed[1].trim());
            String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            super.description = descParsed[0].trim() + " (by: " + formattedDate + ")";
        } catch (Exception e) {
            System.out.println("Unknown Date Format!");
            super.description = descParsed[0].trim() + " (by: " + descParsed[1].trim() + ")";
        }
    }

    @Override
    public String toString() {
        return "[" + TaskType.D + "][" + super.getStatusIcon() + "] " + super.description;
    }
}
