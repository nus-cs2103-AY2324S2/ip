import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime fromTime;
    private LocalDateTime toTime;
    public Event(String description, LocalDateTime fromTime, LocalDateTime toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", getStatusIcon(),
                this.description, this.fromTime.format(dateFormat), this.toTime.format(dateFormat));
    }
    @Override
    public String getSaveLine() {
        return "E " + (this.isDone ? "1 " : "0 ")
                + this.description + " /from "
                + this.fromTime.toString() + " /to "
                + this.toTime.toString() + "\n";
    }

}
