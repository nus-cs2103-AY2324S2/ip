package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Exceptions.DukeException;

public class Event extends Task {
    public Event(String description) throws DukeException {
        super(description);

        String desc = super.description;

        if (!desc.contains("/from") || !desc.contains("/to")) {
            throw new DukeException("Dates missing in description!");
        }

        String[] descParsed = desc.split("/from");
        String[] dateParsed = descParsed[1].split("/to");

        if (dateParsed.length <= 1 || dateParsed[0].isBlank() || dateParsed[1].isBlank()) {
            throw new DukeException("Invalid dates!");
        }

        try {
            LocalDate date1 = LocalDate.parse(dateParsed[0].trim());
            LocalDate date2 = LocalDate.parse(dateParsed[1].trim());

            String formattedDate1 = date1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String formattedDate2 = date2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            super.description = descParsed[0].trim() + " (from: " + formattedDate1 + " to: " + formattedDate2 + ")";
        } catch (Exception e) {
            System.out.println("Unknown Date Format!");
            super.description = descParsed[0].trim() + " (from: " + dateParsed[0].trim() + " to: "
                    + dateParsed[1].trim() + ")";
        }
    }

    @Override
    public String toString() {
        return "[" + TaskType.E + "][" + super.getStatusIcon() + "] " + super.description;
    }
}
