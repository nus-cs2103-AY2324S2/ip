package pan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import pan.enums.TaskStatus;

class Deadlines extends Task {
    private LocalDate byDate;

    /**
     * Constructs a Deadline instance.
     *
     * @param description The description of the deadline.
     * @param isDone The status of whether the deadline has been completed.
     * @param byDate The date at which the deadlien should have been completed by.
     */
    public Deadlines(String description, TaskStatus isDone, LocalDate byDate) {
        super(description, isDone);
        this.byDate = byDate;
    }

    /**
     * Gets the byDate attribute of the event.
     *
     * @return Java LocalDate instance representing the corresponding date that
     *      this given deadline should have been completed by.
     */
    public LocalDate getByDate() {
        return this.byDate;
    }

    /**
     * Updates the byDate attribute of the event.
     *
     * @param newByDate Updated byDate attribute of the event.
     */
    public void setByDate(LocalDate newByDate) {
        this.byDate = newByDate;
    }

    /**
     * Converts the Deadline instance into its correpsonding string representation.
     *
     * @return String that represents whether the corresponding Deadline has been comopleted
     *      as well as its corresponding date that it should have been completed by.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
