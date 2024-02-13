package toothless.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A class inherited from Task with deadline due.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * A public constructor to initialize a new Deadline.
     *
     * @param description A String to describe the task.
     * @param by A LocalDateTime for the deadline due of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * A public constructor to initialize a Deadline with an isDone value.
     *
     * @param description A String to describe the task.
     * @param isDone A Boolean to describe if the task is done.
     * @param by A LocalDateTime for the deadline due of the task.
     */
    public Deadline(String description, boolean isDone, ArrayList<Tag> tags, LocalDateTime by) {
        super(description, isDone, tags);
        this.by = by;
    }

    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | "
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")\n\t"
                + this.getTagsString();
    }
}
