import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime doBy;

    Deadline(String name, LocalDateTime doBy) {
        super(name);
        this.doBy = doBy;
    }

    Deadline(String name, LocalDateTime doBy, boolean done) {
        super(name, done);
        this.doBy = doBy;
    }

    @Override
    public String fileRepresentation() {
        return String.format("D | %s | %s | by: %s",
                this.getStatusIcon(),
                this.getDescription(),
                this.doBy.format(Parser.formatter));
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.doBy.format(Parser.formatter));
    }
}
