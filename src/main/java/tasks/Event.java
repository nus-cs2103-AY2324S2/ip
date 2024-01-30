package tasks;

import java.time.LocalDateTime;
import utilities.DateTimeUtility;

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorageString() {
        int statusValue = this.getStatus() ? 1 : 0;

        return String.format("event~%d~%s~%s~%s", statusValue,
                this.description, DateTimeUtility.inputFormat(this.from), DateTimeUtility.inputFormat(this.to));
    }

    //TODO format the date
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeUtility.outputFormat(this.from) + " to: " + DateTimeUtility.outputFormat(this.to) + ")";
    }
}
