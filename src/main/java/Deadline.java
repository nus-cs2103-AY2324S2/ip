import java.time.LocalDateTime;

public class Deadline extends Task{
    private final LocalDateTime deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, inputFormatter);
    }

    @Override
    public String toFileString() {
        return String.format("D | %s | %s | %s",this.getStatusIcon(),
                this.getDescription(), this.deadline.format(inputFormatter));
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",super.toString(), this.deadline.format(outputFormatter));
    }
}
