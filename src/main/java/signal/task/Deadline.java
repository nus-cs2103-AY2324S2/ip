package signal.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

import signal.SignalException;
import signal.util.TimeManager;

/**
 * Represents a deadline task in the Signal chat-bot.
 * A deadline task has an end time in addition to the properties inherited from Task.
 */
public class Deadline extends Task {
    private TimeManager timeManager = new TimeManager();
    private LocalDate byDate;
    private LocalTime byTime;

    /**
     * Constructor for a new Deadline task.
     *
     * @param description The description of the task.
     * @param by The time that the task is due.
     */
    public Deadline(String description, String by) throws SignalException {
        super(description);
        try {
            String[] parseBy = by.split(" ");
            if (parseBy.length > 1) {
                this.byTime = LocalTime.parse(parseBy[1]);
            }
            this.byDate = LocalDate.parse(parseBy[0]);
        } catch (DateTimeParseException e) {
            throw new SignalException("Oops, I can't read the date or time like that! Please use yyyy-mm-dd for date and hh:mm:ss for time.");
        }
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
                + "| by: " + timeManager.formatDate(byDate) + (byTime != null ? " " + timeManager.formatTime(byTime) : "");
    }

    @Override
    public String checkType() {
        return "Deadline";
    }
}
