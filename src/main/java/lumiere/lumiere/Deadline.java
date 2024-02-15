package lumiere.lumiere;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate byWhen;
    private String byWhen_String;

    public Deadline(String item, boolean marked, LocalDate byWhen, String byWhen_String) {
        super(item, marked);
        this.byWhen = byWhen;
        this.byWhen_String = byWhen_String;
    }

    public String getbyWhen() {
        return this.byWhen_String;
    }

    @Override
    public String stringify() {
        String m = "";
        if (super.isMarked())
            m = "[X]";
        else
            m = "[ ]";
        return "[D]" + m + " " + super.stringify() + " (by: "
                + this.byWhen.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
