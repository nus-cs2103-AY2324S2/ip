import java.time.LocalDateTime;

public class Event extends Task{
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(boolean isDone, String description, LocalDateTime start, LocalDateTime end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(Duke.outputdtFormatter) + " to: " + end.format(Duke.outputdtFormatter) + ")";
    }

    @Override
    public String toSave() {
        return "[E]|" + super.toSave() + "|" + start.format(Duke.inputdtFormatter) + "|" + end.format(Duke.inputdtFormatter);
    }
}
