package grumblebug;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructor for To-do type task.
     * @param done Doneness of task.
     * @param description Description of task.
     */
    public Event(boolean done, String description, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.isDone = done;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * To return full status representing an Event task.
     * @return a String that is readable easily, showing task info.
     */
    public String getFullStatus() {
        String status = ". [" + this.getStatusIcon() + "][" + this.getTaskType() + "] " + this.description
                + ", period: " + this.startDate + " to " + this.endDate;
        return status;
    }

    /**
     * Getter for start date of a Event task.
     * @return LocalDate object of deadline.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Getter for end date of a Event task.
     * @return LocalDate object of deadline.
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Representation for task type, for text storage purposes.
     * @return a Character representing the Event task type.
     */
    @Override
    public char getTaskType() {
        return 'E';
    }
}
