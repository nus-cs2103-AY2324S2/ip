package duke.tasks;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.List;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalTime to;

    public Event (String description, String from, String to) throws IllegalArgumentException {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseTime(to);
    }

    //TODO: Diversify input options for /to
    public Event (String description, boolean done, String from, String to) {
        super(description);
        super.updateIsDone(done);
        this.from = parseDateTime(from);
        this.to = parseTime(to);
    }

    @Override
    public String getSaveTask() {
        return "E | " + super.getSaveTask() + " | "
                + from.toString().replace("T", " ") + " | " + to;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] "
                + this.description + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }

    private LocalTime parseTime(String time) {
        List<String> timeCombinations = Arrays.asList("HH:mm", "HHmm", "hh:mm a");

        for (String t : timeCombinations) {
            try {
                return LocalTime.parse(time, DateTimeFormatter.ofPattern(t));
            } catch (DateTimeParseException dt) {
            }
        }

        return null;
    }
}