package duke.tasks;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Event task template.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalTime to;

    /**
     * Event constructor.
     *
     * @param description   Task name or description of task.
     * @param from          Date and time the event starts.
     * @param to            Time the event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseTime(to);
    }


    /**
     * Event constructor.
     *
     * @param description   Task name or description of task.
     * @param done          Marks task as completed/uncompleted. [True: complete, False: uncompleted]
     * @param from          Date and time the event starts.
     * @param to            Time the event ends.
     */
    public Event(String description, boolean done, String from, String to) {
        super(description);
        super.updateIsDone(done);
        this.from = parseDateTime(from);
        this.to = parseTime(to);
    }

    /**
     * Format Event as a string to be saved to file.
     * @return saveTask     Returns the task as a string in the format compatible with file.
     */
    @Override
    public String saveFileString() {
        return "E | " + super.saveFileString() + " | "
                + from.toString().replace("T", " ") + " | " + to;
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] "
                + this.description + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }

    /**
     * Parse and convert string to LocalTime object.
     *
     * @param time      String containing time.
     * @return time     LocalTime object.
     */
    private LocalTime parseTime(String time) {
        List<String> timeCombinations = Arrays.asList("HH:mm", "HHmm", "hh:mm a");

        for (String t : timeCombinations) {
            try {
                return LocalTime.parse(time, DateTimeFormatter.ofPattern(t));
            } catch (DateTimeParseException dt) {
                // ...
            }
        }

        return null;
    }
}
