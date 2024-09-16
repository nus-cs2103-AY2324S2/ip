package duke.tasks;

import java.time.LocalDateTime;

import duke.TaskList;

/**
 * The DeadlineTask class represents a task with a deadline description and end datetime.
 * Inherits from the Task class.
 */
public class DeadlineTask extends Task {
    private LocalDateTime end;

    /**
     * Constructs a DeadlineTask object with the specified description and end datetime.
     *
     * @param desc the description of the deadline task
     * @param end the end datetime of the deadline task
     */
    public DeadlineTask(String desc, LocalDateTime end) {
        super(desc);;
        this.end = end;
    }

    /**
     * Constructs a DeadlineTask object with the specified description, completion status, and end datetime.
     *
     * @param desc the description of the deadline task
     * @param isDone the completion status of the deadline task ("1" for done, "0" for not done)
     * @param end the end datetime of the deadline task
     */
    public DeadlineTask(String desc, String isDone, LocalDateTime end) {
        super(desc, isDone);
        this.end = end;
    }

    /**
     * Gets the status icon of the deadline task.
     *
     * @return the status icon of the deadline task ("[D][X]" if done, "[D][ ]" if not done)
     */
    public String getStatusIcon() {
        return (this.isDone() ? "[D][X] " : "[D][ ] ");
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return a string representation of the deadline task, including its status icon, description, and end datetime
     */
    public String toString() {
        return this.getStatusIcon() + this.getDesc() + " (by: "
                + Task.toStringDateTime(this.end) + ")";
    }

    /**
     * Returns a string in a standardised format to represent the deadline task for saving to file.
     *
     * @param tasks this param is not used in this method
     * @return a string in a standardised format representing the deadline task
     */
    public String save(TaskList tasks) {
        String isDone = this.isDone() ? "1" : "0";
        return "D," + isDone + "," + this.getDesc() + "," + this.end;
    }
}
