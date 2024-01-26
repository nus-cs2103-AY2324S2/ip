public class Deadline extends Task {
    private String byWhen;

    public Deadline(String item, String byWhen) {
        super(item);
        this.byWhen = byWhen;
    }

    @Override
    public String stringify() {
        String m = "";
        if (super.isMarked())
            m = "[X]";
        else
            m = "[ ]";
        return "[D]" + m + " " + super.stringify() + " (by: " + this.byWhen + ")";
    }
}
