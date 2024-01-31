import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");


    public Event(String description, String from, String to) throws HenryException {
        super(description);

        if (from.isEmpty() || from.equals(" ")) {
            throw new HenryException("Missing time !!!\n");
        } else if (to.isEmpty() || to.equals(" ")) {
            throw new HenryException("Missing time!!!\n");
        }

        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(OUTPUT_FORMATTER), to.format(OUTPUT_FORMATTER));
    }

    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s", super.toFileString(), from.format(INPUT_FORMATTER), to.format(INPUT_FORMATTER));
    }
}