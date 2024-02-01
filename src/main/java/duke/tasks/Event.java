package duke.tasks;

import java.time.LocalDateTime;

import duke.common.Utils;

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

        return String.format("event~%d~%s~%s~%s", statusValue, this.description,
                Utils.inputFormat(this.from), Utils.inputFormat(this.to));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Utils.outputFormat(this.from) + " to: "
                + Utils.outputFormat(this.to) + ")";
    }
}
