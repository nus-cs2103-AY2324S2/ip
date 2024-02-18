package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import common.DukeException;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    DateTimeFormatter receivingFormatter = DateTimeFormatter.ofPattern("d/M/yyyy-HHmm");
    DateTimeFormatter printingFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    public Event(String taskName, String startTime, String endTime) throws DukeException {
        super(taskName);
        try {
            this.startTime = LocalDateTime.parse(startTime, receivingFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Start time not in the correct format.\n"
                    + "Correct format: dd/MM/yyyy-HHmm\n"
                    + "Received: " + startTime + "\n"
                    + "\"" + taskName + "\" not added to the list.");
        }

        try {
            this.endTime = LocalDateTime.parse(endTime, receivingFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("End time not in the correct format.\n"
                    + "Correct format: dd/MM/yyyy HHmm\n"
                    + "Received: " + endTime + "\n"
                    + "\"" + taskName + "\" not added to the list.");
        }
    }

    public Event(boolean isDone, String taskName, String time) throws DukeException {
        super(isDone, taskName);
        try {
            String[] startToEndTime = time.split(" - ");
            startTime = LocalDateTime.parse(startToEndTime[0], printingFormatter);
            endTime = LocalDateTime.parse(startToEndTime[1], printingFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date and Time not in the correct format.\n"
                    + "Correct format: MMM dd yyyy, HH:mm\n"
                    + "Received: " + taskName + " | " + time + "\n"
                    + "\"" + taskName + "\" removed from the list.");
        }
    }

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
