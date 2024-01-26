import java.time.LocalDateTime;

public class Deadline extends Task {
    LocalDateTime by;


    public Deadline(String taskName, LocalDateTime by) {
        this(taskName, by, false);
    }

    public Deadline(String taskName, LocalDateTime by, Boolean done) {
        super(taskName, done);
        super.identifier = "D";
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s by: %s", super.toString(), TimeProcessor.toString(by));
    }


    @Override 
    public String[] encode() {
        String[] encodedDeadline = new String[4];
        String[] encodedTask = super.encode();

        for (int i = 0; i < encodedTask.length; i++) {
            encodedDeadline[i] = encodedTask[i];
        }

        encodedDeadline[3] = TimeProcessor.toString(by);

        return encodedDeadline;
    }

    
}
