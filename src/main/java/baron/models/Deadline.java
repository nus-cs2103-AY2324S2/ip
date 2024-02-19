package baron.models;

import java.time.LocalDateTime;

import baron.utils.DateUtils;

/**
 * Deadline takes in a name and a deadline
 * This class has date formatting.
 */
public class Deadline extends Task {

    private final LocalDateTime deadline;

    /**
     * Creates a deadline. isDone defaults to false
     * @param name name of deadline
     * @param deadline deadline of deadline
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Creates a deadline from scratch.
     * @param id id of deadline
     * @param name name of deadline
     * @param isDone whether deadline is done or not
     * @param deadline deadline of deadline
     */
    public Deadline(int id, String name, boolean isDone, LocalDateTime deadline) {
        super(id, name, isDone);
        this.deadline = deadline;
    }

    /**
     * Parses data string to create a deadline object.
     *
     * @param data deadline
     * @return Created Deadline object
     */
    public static Deadline fromDataString(String data) {
        String[] segments = data.split("\\s*\\|\\s*");
        int id = Integer.parseInt(segments[0]);
        boolean done = Long.parseLong(segments[1]) == 1;
        // Strong assumption that there is no | in the data
        String name = segments[2];
        LocalDateTime deadline = DateUtils.parseDate(segments[3]);
        return new Deadline(id, name, done, deadline);
    }

    @Override
    public String toDataString() {
        return super.toDataString() + " | " + this.deadline.format(DateUtils.INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateUtils.formatDate(deadline) + ")";
    }
}
