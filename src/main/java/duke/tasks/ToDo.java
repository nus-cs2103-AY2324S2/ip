package duke.tasks;

import java.time.LocalDate;

/**
 * A class that represents a ToDo task, a type of Task in a task management application.
 * Inherits from the Task class.
 * @author SITHANATHAN RAHUL
 * @version CS2103T AY23/24 Semester 2
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     * @param description A String describing the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the ToDo task, indicating its completion status.
     * @return A formatted String indicating the completion status and description of the ToDo task.
     */
    @Override
    public String toString() {
        if (this.isMarked()) {
            return "[T][X] " + super.toString();
        } else {
            return "[T][ ] " + super.toString();
        }
    }

    /**
     * Returns a String identifier for the ToDo task.
     * @return A String identifier specific to ToDo tasks.
     */
    @Override
    public String identifier() {
        return "[T]";
    }

    /**
     * Returns the deadline of the ToDo task.
     * Since ToDo tasks do not have deadlines, it returns the current date.
     * @return LocalDate representing the current date.
     */
    @Override
    public LocalDate getDeadline() {
        return LocalDate.now();
    }
}
