package toothless.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A class inherited from Task with start and end datetime.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * A public constructor to initialize a new Event.
     *
     * @param description A String to describe the task.
     * @param from A LocalDateTime for the start of the Event.
     * @param to A LocalDateTime for the end of the Event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * A public constructor to initialize a new Event with an isDone value.
     *
     * @param description A String to describe the task.
     * @param isDone A Boolean to describe if the task is done.
     * @param from A LocalDateTime for the start of the Event.
     * @param to A LocalDateTime for the end of the Event.
     */
    public Event(String description, boolean isDone, ArrayList<Tag> tags, LocalDateTime from, LocalDateTime to) {
        super(description, isDone, tags);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorageString() {
        return "E | " + super.toStorageString() + String.format(" | %s | %s",
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)\n\t",
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")))
                + this.getTagsString();
    }
}
