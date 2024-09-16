package task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime doBy;

    public Deadline(String name, LocalDateTime doBy) {
        super(name);
        this.doBy = doBy;
    }

    public Deadline(String name, LocalDateTime doBy, boolean done) {
        super(name, done);
        this.doBy = doBy;
    }


    @Override
    public String fileRepresentation() {
        return String.format("D | %s | %s | by: %s",
                this.getStatusIcon(),
                this.getDescription(),
                this.doBy.format(Task.formatter));
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.doBy.format(Task.formatter));
    }
}
