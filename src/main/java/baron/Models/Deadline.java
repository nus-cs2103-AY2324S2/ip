package baron.Models;

import baron.Utils.DateUtils;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private final LocalDateTime deadline;

    public Deadline (String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline (int id, String name, boolean done, LocalDateTime deadline) {
        super(id, name, done);
        this.deadline = deadline;
    }

    public static Deadline fromDataString (String data) {
        String[] segments = data.split("\\s*\\|\\s*");
        int id = Integer.parseInt(segments[0]);
        boolean done = Long.parseLong(segments[1]) == 1;
        // Strong assumption that there is no | in the data
        String name = segments[2];
        LocalDateTime deadline = DateUtils.parseDate(segments[3]);
        return new Deadline(id, name, done, deadline);
    }

    @Override
    public String toString () {
        return "[D]" + super.toString() + " (by: " + DateUtils.formatDate(deadline) + ")";
    }

    public String toDataString () {
        return super.toDataString() + " | " + this.deadline.format(DateUtils.INPUT_FORMATTER);
    }
}
