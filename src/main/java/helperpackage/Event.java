package helperpackage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    DateTimeFormatter receivingFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    DateTimeFormatter printingFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    
    public Event(String description) throws DukeException {
        super(description.substring(0, description.indexOf("/")).trim());
        int indexOfFrom = description.indexOf("/from");
        int indexOfTo = description.indexOf("/to");

        String start = description.substring(indexOfFrom + 6, indexOfTo).strip();
        if (indexOfFrom != -1) {
            try {
                startTime = LocalDateTime.parse(start, receivingFormatter);
            } catch (DateTimeParseException e) {
                throw new DukeException("Start time not in the correct format.\n"
                        + "Correct format: dd/MM/yyyy HHmm\n"
                        + "Received: " + description + "\n"
                        + "Event not added to the list.");
            }
        } else {
            throw new DukeException("Invalid event input. :(");
        }

        String end = description.substring(indexOfTo + 4).strip();
        if (indexOfTo != -1 && indexOfTo + 4 < description.length()) {
            try {
                endTime = LocalDateTime.parse(end, receivingFormatter);
            } catch (DateTimeParseException e) {
                throw new DukeException("End time not in the correct format.\n"
                        + "Correct format: dd/MM/yyyy HHmm\n"
                        + "Received: " + description + "\n"
                        + "Event not added to the list.");
            }
        } else {
            throw new DukeException("Invalid Event input. :(");
        }
    }

    public Event(boolean isDone, String description, String time) throws DukeException{
        super(isDone, description);
        try {
            String[] startToEndTime = time.split(" - ");
            startTime = LocalDateTime.parse(startToEndTime[0], printingFormatter);
            endTime = LocalDateTime.parse(startToEndTime[1], printingFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date and Time not in the correct format.\n"
                    + "Correct format: MMM dd yyyy, HH:mm\n"
                    + "Received: " + description + " | " + time + "\n"
                    + "Event removed from the list.");
        }    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(printingFormatter) 
                + " to: " + endTime.format(printingFormatter) + ")";
    }

    @Override
    public String toData() {
        return "E | " + super.toData() + " | " + startTime.format(printingFormatter) 
                + " - " + endTime.format(printingFormatter); 
    }
}
