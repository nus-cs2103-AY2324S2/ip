package task;

import java.time.LocalDateTime;

public class DoAfter extends Task {
    private LocalDateTime doAfter;

    public DoAfter(String name, LocalDateTime doAfter) {
        super(name);
        this.doAfter = doAfter;
    }

    public DoAfter(String name, LocalDateTime doAfter, boolean done) {
        super(name, done);
        this.doAfter = doAfter;
    }

    @Override
    public String fileRepresentation() {
        return String.format("A | %s | %s | after: %s",
                this.getStatusIcon(),
                this.getDescription(),
                this.doAfter.format(Task.formatter));
    }
    @Override
    public String toString() {
        return String.format("[A]%s (after: %s)", super.toString(),
                this.doAfter.format(Task.formatter));
    }
}
