package seedu.mamta;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 * Inherits properties and methods from the Task class.
 */
public class Deadline extends Task {

    /**
     * The deadline of the task.
     */
    private final String deadline;

    /**
     * Constructs a Deadline object with the given content and deadline.
     * @param content The content of the task.
     * @param deadline The deadline of the task.
     */
    Deadline(String content, String deadline) {
        super(content);
        if (deadline.isEmpty()) { //handling the case where deadline does not get a valid deadline
            this.deadline = String.valueOf(MamtaException.invalidDates());
        } else {
            this.deadline = transformDates(deadline);
        }
    }

    /**
     * Constructs a Deadline object with the given completion status, content, and deadline.
     * @param isComplete The completion status of the task.
     * @param content The content of the task.
     * @param deadline The deadline of the task.
     */
    Deadline(boolean isComplete, String content, String deadline) {
        super(isComplete, content);
        this.deadline = transformDates(deadline);
    }

    /**
     * Transforms the input deadline into a standardized format.
     * @param deadline The input deadline.
     * @return The transformed deadline in a standardized format.
     */
    public String transformDates(String deadline) {
        String year = "";
        String month = "";
        String day = "";
        String time = "";
        try {
            String[] splitOutput = deadline.split("-");
            for (String s : splitOutput) {
                year = splitOutput[0];
                month = splitOutput[1];
                day = splitOutput[2].split(" ")[0];
                time = splitOutput[2].split(" ")[1];
            }
            LocalDate date = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
            year = String.valueOf(date.getYear());
            month = String.valueOf(date.getMonth());
            day = String.valueOf(date.getDayOfMonth());
            return String.format("%s %s %s %s", month, day, year, time);
        } catch (Exception e) {
            return deadline;
        }
    }

    /**
     * Marks the task as done.
     * @return A new Deadline object with the task marked as done.
     */
    @Override
    public Deadline markDone() {
        return new Deadline(true, this.content, this.deadline);
    }

    /**
     * Marks the task as not done.
     * @return A new Deadline object with the task marked as not done.
     */
    @Override
    public Deadline unmarkTask() {
        return new Deadline(false, this.content, this.deadline);
    }

    /**
     * Returns a string representation of the Deadline object.
     * @return A string representation of the Deadline object.
     */
    public String toString() {
        if (this.isComplete) {
            return String.format("D|X|%s|%s", this.content, this.deadline);
        } else {
            return String.format("D| |%s|%s", this.content, this.deadline);
        }
    }
}
