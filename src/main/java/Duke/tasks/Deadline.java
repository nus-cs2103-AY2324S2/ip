/**
 * Represents a task with a deadline.
 * This class extends the Task class and adds functionality for tasks with specific deadlines.
 */
package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for deadline
 */
public class Deadline extends Task {
    private LocalDateTime deadlineDate;
    private String deadline;
    /**
     * Constructs a Deadline object with the given description and deadline string.
     *
     * @param descr    The description of the task.
     * @param deadline The deadline string in the format "dd/MM/yyyy HHmm".
     */
    public Deadline(String descr, String deadline) {
        super(descr);
        deadline = deadline.trim();
        if (isValidDateFormat(deadline)) {
            String[] dateNumbers = deadline.split("[/ ]");
            this.deadlineDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
        } else {
            this.deadline = deadline;
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
        if (isValidDateFormat(deadline)) {
            String[] dateNumbers = deadline.split("[/ ]");
            this.deadlineDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
        } else {
            this.deadline = deadline;
        }
    }

    /**
     * Checks if the given deadline string has a valid date format.
     *
     * @param deadline The deadline string to be checked.
     * @return True if the deadline string has a valid format, otherwise false.
     */
    private static boolean isValidDateFormat(String deadline) {
        if (deadline.length() <= 12 || deadline.length() >= 16) {
            return false;
        }
        String[] dateNumbers = deadline.split("[/ ]");
        if (dateNumbers.length != 4) {
            return false;
        }
        try {
            for (String i : dateNumbers) {
                Integer.parseInt(i);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        int time = Integer.parseInt(dateNumbers[3]);
        if (time >= 2400 || time < 0) {
            return false;
        }
        return true;
    }
    /**
     * Writes the Deadline object into a string format for storage.
     *
     * @return A string representing the Deadline object.
     */
    @Override
    public String writeObject() {
        if (deadlineDate != null) {
            return String.format("deadline %s | %s\n",
                    super.writeObject(),
                    this.deadlineDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        } else {
            return String.format("deadline %s | %s\n", super.writeObject(), this.deadline);
        }
    }

    /**
     * Converts the Deadline object into a string representation.
     *
     * @return A string representing the Deadline object.
     */
    @Override
    public String toString() {
        if (deadlineDate != null) {
            return String.format("[D]%s(by: %s)", super.toString(),
                    this.deadlineDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
        }
        return String.format("[D]%s(by: %s)", super.toString(), this.deadline);
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
}
