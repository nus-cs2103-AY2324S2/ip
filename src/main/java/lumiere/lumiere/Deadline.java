package lumiere.lumiere;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate byWhen;
    private String byWhen_String;

    /**
     * Constructor for Deadline object
     * 
     * @param item          The item that describes what the deadline is of, i.e.
     *                      what
     *                      needs to be done.
     * @param marked        A boolean that indicates whether this deadline is marked
     *                      as
     *                      over or not.
     * @param byWhen        A LocalDate object that indicates when the deadline is.
     * @param byWhen_String A string that indicates when the deadline is.
     * @return Nothing, it is a constructor.
     */
    public Deadline(String item, boolean marked, LocalDate byWhen, String byWhen_String) {
        super(item, marked);
        this.byWhen = byWhen;
        this.byWhen_String = byWhen_String;
    }

    /**
     * An instance method that returns when the time of the deadline is.
     * 
     * @return A string that represents when the deadline is.
     */
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
