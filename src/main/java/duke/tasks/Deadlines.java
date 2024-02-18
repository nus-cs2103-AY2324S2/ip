package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class representing a Deadlines task, a type of Task in a task management application.
 * Inherits from the Task class.
 * Provides additional functionality to handle tasks with specific deadlines.
 * @author SITHANATHAN RAHUL
 * @version CS2103T AY23/24 Semester 2
 */
public class Deadlines extends Task {
    private LocalDateTime actualDeadline;

    /**
     * Constructs a Deadlines task with the given description and deadline.
     * The task is initially marked as not completed.
     *
     * @param description    A String describing the Deadlines task.
     * @param actualDeadline The LocalDateTime representing the deadline of the task.
     */
    public Deadlines(String description, LocalDateTime actualDeadline) {
        super(description);
        this.actualDeadline = actualDeadline;
    }

    /**
     * Returns the deadline date of the Deadlines task.
     *
     * @return LocalDate representing the deadline date of the task.
     */
    @Override
    public LocalDate getDeadline() {
        return this.getAbsoluteDeadline().toLocalDate();
    }

    /**
     * Returns the absolute deadline of the Deadlines task.
     *
     * @return LocalDateTime representing the absolute deadline of the task.
     */
    public LocalDateTime getAbsoluteDeadline() {
        return this.actualDeadline;
    }

    /**
     * Returns a String representation of the Deadlines task, indicating its completion status and deadline.
     *
     * @return A formatted String indicating the completion status, description, and deadline of the Deadlines task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm'hrs'");
        if (this.isMarked()) {
            return "[D][X] " + super.toString() + "(by: " + this.getAbsoluteDeadline().format(outputFormatter) + ")";
        } else {
            return "[D][ ] " + super.toString() + "(by: " + this.getAbsoluteDeadline().format(outputFormatter) + ")";
        }
    }

    /**
     * Returns a String identifier for the Deadlines task.
     *
     * @return A String identifier specific to Deadlines tasks.
     */
    @Override
    public String identifier() {
        return "[D]";
    }
}
