import java.time.LocalDateTime;

public class Event extends Task {
    LocalDateTime from;
    LocalDateTime to;


    public Event(String taskName, LocalDateTime from, LocalDateTime to) {
        this(taskName, from, to, false);
    }

    public Event(String taskName, LocalDateTime from, LocalDateTime to, Boolean done) {
        super(taskName, done);
        super.identifier = "E";
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("%s from: %s to: %s", super.toString(), TimeProcessor.toString(from), TimeProcessor.toString(to));
    }

    @Override
    public String[] encode() {
        String[] encodedEvent = new String[6];
        String[] encodedTask = super.encode();

        for (int i = 0; i < encodedTask.length; i++) {
            encodedEvent[i] = encodedTask[i];
        }

        encodedEvent[4] = TimeProcessor.toString(from);
        encodedEvent[5] = TimeProcessor.toString(to);

        return encodedEvent;
    }
}
