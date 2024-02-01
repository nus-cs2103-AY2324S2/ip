package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /** LocalDateTime Object of when the Deadline Tasks is marked to be done by. */
    private LocalDateTime byDate;

    /**
     * Constructs a Deadline Object.
     *
     * @param description String containing the description of the Task.
     * @param byDate LocalDateTime Object of when the Deadline Tasks is marked to be done by.
     */
    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns the Deadline Object as a String.
     *
     * @return String Representation of the Task Object.
     */
    @Override
    public String toString() {
        DateTimeFormatter dTFormatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        String byDateString = byDate.format(dTFormatter);
        return String.format("[D]" + super.toString() + " (by: %s)", byDateString);
    }
}
