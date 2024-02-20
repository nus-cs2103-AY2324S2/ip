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
     * @param description    The description of the task.
     * @param deadline The deadline string in the format "dd/MM/yyyy HHmm".
     */
    public Deadline(String description, String deadline) {
        super(description);
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
     * @param dateString The dateString to be checked.
     * @return True if the dateString has a valid format, otherwise false.
     */
    private static boolean isValidDateFormat(String dateString) {
        boolean isShorterThanMinimum = dateString.length() <= 12;
        boolean isLongerThanMaximum = dateString.length() >= 16;
        if (isShorterThanMinimum || isLongerThanMaximum) {
            return false;
        }
        String[] dateNumbers = dateString.split("[/ ]");
        boolean hasIncorrectDateFormatNumbers = dateNumbers.length != 4;
        if (hasIncorrectDateFormatNumbers) {
            return false;
        }
        boolean isDateNumberAllIntegers = checkArrayContainIntegers(dateNumbers);
        if (!isDateNumberAllIntegers) {
            return false;
        }
        int time = Integer.parseInt(dateNumbers[3]);
        if (!checkValidInteger24hourFormat(time)) {
            return false;
        }
        return true;
    }
    private static boolean checkArrayContainIntegers(String[] inputs) {
        try {
            for (String input : inputs) {
                Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    private static boolean checkValidInteger24hourFormat(int time) {
        boolean isNegative = time < 0;
        boolean isMoreThan2400 = time >= 2400;
        if (isNegative || isMoreThan2400) {
            return false;
        }
        int numberOfMinutes = 60;
        boolean hasValidMinutes = (time % 100) < numberOfMinutes;
        return hasValidMinutes;
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
