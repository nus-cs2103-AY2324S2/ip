package signal.task;

import java.time.LocalDate;
import java.time.LocalTime;

import static signal.Duke.formatDate;
import static signal.Duke.formatTime;

/**
 * Represents a deadline task in the Signal chat-bot.
 * A deadline task has an end time in addition to the properties inherited from Task.
 */
public class Deadline extends Task {
    private LocalDate byDate;
    private LocalTime byTime;

    /**
     * Constructor for a new Deadline task.
     *
     * @param description The description of the task.
     * @param by The time that the task is due.
     */
    public Deadline(String description, String by) {
        super(description);
        String[] parseBy = by.split(" ");
        if (parseBy.length > 1) {
            this.byTime = LocalTime.parse(parseBy[1]);
        }
        this.byDate = LocalDate.parse(parseBy[0]);

//            this.by = by;
    }

    @Override
    public LocalDate getDue() {
        return this.byDate;
    }

    /**
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "D" + super.toString()
                + " | by: " + formatDate(byDate) + (byTime != null ? " " + formatTime(byTime) : "");
    }

    @Override
    public String checkType() {
        return "Deadline";
    }
}
