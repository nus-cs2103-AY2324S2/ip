package tasks;

import java.time.LocalDateTime;
import common.Utils;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toStorageString() {
        int statusValue = this.getStatus() ? 1 : 0;

        return String.format("deadline~%d~%s~%s", statusValue,
                this.description, Utils.inputFormat(this.by));
    }

    //TODO format the date
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Utils.outputFormat(this.by) + ")";
    }

}
