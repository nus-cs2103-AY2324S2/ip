/**
 * Represents a task with a deadline.
 * This class extends the Task class and adds functionality for tasks with specific deadlines.
 */
package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.InvalidDeadlineException;

/**
 * Class for deadline
 */
public class Deadline extends Task {
    private LocalDateTime deadlineDate;
    /**
     * Constructs a Deadline object with the given description and deadline string.
     *
     * @param description    The description of the task.
     * @param deadline The deadline string in the format "dd/MM/yyyy HHmm".
     */
    public Deadline(String description, String deadline) throws InvalidDeadlineException {
        super(description);
        deadline = deadline.trim();
        String[] dateNumbers = deadline.split("[/ ]");
        try {
            this.deadlineDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
        } catch (DateTimeException e) {
            throw new InvalidDeadlineException();
        }
    }
    /**
     * Constructs a Deadline object with the given status, description, and deadline string.
     *
     * @param status   The status of the task.
     * @param descr    The description of the task.
     * @param deadline The deadline string in the format "dd/MM/yyyy HHmm".
     */
    public Deadline(String status, String descr, String deadline) {
        super(descr);
        super.setStatus(status);
        deadline = deadline.trim();
        String[] dateNumbers = deadline.split("[/ ]");
        int year = Integer.parseInt(dateNumbers[2]);
        int month = Integer.parseInt(dateNumbers[1]);
        int day = Integer.parseInt(dateNumbers[0]);
        int hour = Integer.parseInt(dateNumbers[3].substring(0, 2));
        int min = Integer.parseInt(dateNumbers[3].substring(2));
        this.deadlineDate = LocalDateTime.of(
                year, month, day, hour, min);
    }
    /**
     * Writes the Deadline object into a string format for storage.
     *
     * @return A string representing the Deadline object.
     */
    @Override
    public String writeObject() {
        assert this.deadlineDate != null;
        return String.format("deadline %s | %s\n",
                super.writeObject(),
                this.deadlineDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
    }
    /**
     * Converts the Deadline object into a string representation.
     *
     * @return A string representing the Deadline object.
     */
    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(),
                this.deadlineDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
    }
    /**
     * Checks if the Deadline object has the specified date.
     *
     * @param toFind The date to find in the Deadline object.
     * @return True if the Deadline object has the specified date, otherwise false.
     */
    @Override
    public boolean hasDate(LocalDateTime toFind) {
        return toFind.equals(this.deadlineDate);
    }
    public LocalDateTime getDeadlineDate() {
        return this.deadlineDate;
    }
    @Override
    public int compareTo(Task task) {
        if (task instanceof ToDo) {
            return -1;
        }
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            LocalDateTime compareDate = deadlineTask.getDeadlineDate();
            if (this.deadlineDate.isBefore(compareDate)) {
                return -1;
            }
            if (this.deadlineDate.isAfter(compareDate)) {
                return 1;
            } else {
                return 0;
            }
        }
        if (task instanceof Event) {
            Event eventTask = (Event) task;
            LocalDateTime compareDate = eventTask.getEndDate();
            if (this.deadlineDate.isBefore(compareDate)) {
                return -1;
            }
            if (this.deadlineDate.isAfter(compareDate)) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
