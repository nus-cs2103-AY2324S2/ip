package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task that has a date of completion. Essentially a Task with a Date.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Deadline constructor method.
     * @param name The name of this task.
     * @param by When this task needs to be completed, as a String formatted in the form of Input.INPUT_TIME_FORMATTER
     * @throws DateTimeParseException when an invalid date according to the INPUT_TIME_FORMATTER
     */
    public Deadline(String name, String by) throws DateTimeParseException {
        super(name);
        assert by != null : "by cannot be null";
        assert !by.contains("\n") : "by cannot contain newline characters";
        this.by = LocalDateTime.parse(by, Task.INPUT_TIME_FORMATTER);
    }

    /**
     * Returns a Descriptor string containing details of this task. Meant for printing to the console.
     * @return a String containing details of this method, showing the date this task is due
     */
    @Override
    public String describe() {
        return String.format("[D]%s (by: %s)",
                super.describe(), this.by.format(Task.OUTPUT_TIME_FORMATTER)
        );
    }

    /**
     * Returns a string representation containing all details required to reconstruct this task. Meant for writing to
     * storage.
     * @return a String that shows the name, date and done status of the task, to be stored on disk.
     */
    @Override
    public String toStorageString() {
        return String.format("D,%s,%s",
                super.toStorageString(), this.by.format(Task.INPUT_TIME_FORMATTER));
    }
}
