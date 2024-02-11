package lumiere;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String byWhen;
    private LocalDate deadline;

    public Deadline(String item, boolean marked, String byWhen) {
        super(item, marked);
        this.byWhen = byWhen;
        this.deadline = LocalDate.parse(byWhen);
    }

    public String getbyWhen() {
        return this.byWhen;
    }

    @Override
    public String stringify() {
        String m = "";
        if (super.isMarked())
            m = "[X]";
        else
            m = "[ ]";
        return "[D]" + m + " " + super.stringify() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
