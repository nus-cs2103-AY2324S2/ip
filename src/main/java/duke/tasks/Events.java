package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class representing an Events task, a type of Task in a task management application.
 * Inherits from the Task class.
 * Provides additional functionality to handle events with specific start and end times.
 * @author SITHANATHAN RAHUL
 * @version CS2103T AY23/24 Semester 2
 */
public class Events extends Task {
    private LocalDateTime actualStart;
    private LocalDateTime actualEnd;

    /**
     * Constructs an Events task with the given description, start time, and end time.
     * The task is initially marked as not completed.
     * @param description  A String describing the Events task.
     * @param actualStart  The LocalDateTime representing the start time of the event.
     * @param actualEnd    The LocalDateTime representing the end time of the event.
     */
    public Events(String description, LocalDateTime actualStart, LocalDateTime actualEnd) {
        super(description);
        this.actualStart = actualStart;
        this.actualEnd = actualEnd;
    }

    /**
     * Returns a String representation of the Events task, indicating its completion status and event times.
     * @return A formatted String indicating the completion status, description, and event times of the Events task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm'hrs'");
        if (this.isMarked()) {
            return "[E][X] "
                + super.toString()
                +
                "(from: " + this.actualStart.format(outputFormatter)
                +
                " to: " + this.actualEnd.format(outputFormatter)
                + ")";
        } else {
            return "[E][ ] "
                + super.toString()
                + "(from: " + this.actualStart.format(outputFormatter)
                + ", to: " + this.actualEnd.format(outputFormatter) + ")";
        }
    }

    /**
     * Returns a String identifier for the Events task.
     * @return A String identifier specific to Events tasks.
     */
    @Override
    public String identifier() {
        return "[E]";
    }

    public LocalDateTime getStart() {
        return this.actualStart;
    }

    /**
     * Returns the deadline of the Events task.
     * Since Events tasks do not have traditional deadlines, it returns null.
     * @return null since Events tasks do not have traditional deadlines.
     */
    @Override
    public LocalDate getDeadline() {
        return null;
    }
}
