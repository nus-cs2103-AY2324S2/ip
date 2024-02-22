package anxi.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import anxi.command.AnxiException;
import anxi.handlers.Handler;

/**
 * Deadline task template.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Deadline constructor.
     *
     * @param description   Task name or description of task.
     * @param by            Date and time task has to be completed by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Deadline constructor.
     *
     * @param description   Task name or description of task.
     * @param done          Marks task as completed/uncompleted. [True: complete, False: uncompleted]
     * @param by            Date and time task has to be completed by.
     */
    public Deadline(String description, boolean done, String by) {
        super(description);
        super.updateIsDone(done);

        try {
            Handler h = new Handler();
            this.by = h.parseDateTime(by);
        } catch (AnxiException de) {
            // Since reading from file, not errors will be reported.
        }
    }

    public LocalDate getByDate() {
        return this.by.toLocalDate();
    }

    /**
     * Formats Deadline as a string to be saved to file.
     * @return saveTask     Returns the task as a string in the format compatible with file.
     */
    @Override
    public String saveFileString() {
        return "D | " + super.saveFileString() + " | "
                + by.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + this.description + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
    }
}
