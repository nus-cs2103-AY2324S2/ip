package grumblebug;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructor for To-do type task.
     * @param done Doneness of task.
     * @param description Description of task.
     */
    public Deadline(boolean done, String description, LocalDate date) {
        this.description = description;
        this.isDone = done;
        this.date = date;
    }

    /**
     * To return full status representing a Deadline task.
     * @return a String that is readable easily, showing task info.
     */
    @Override
    public String getFullStatus() {
        String status = ". [" + this.getStatusIcon() + "][" + this.getTaskType() + "] " + this.description
                + ", by: " + this.date;
        return status;
    }

    /**
     * Getter for deadline of a Deadline task.
     * @return LocalDate object of deadline.
     */
    public LocalDate getDeadline() {
        return this.date;
    }

    /**
     * Representation for task type, for text storage purposes.
     * @return a Character representing the Deadline task type.
     */
    @Override
    public char getTaskType() {
        return 'D';
    }
}
