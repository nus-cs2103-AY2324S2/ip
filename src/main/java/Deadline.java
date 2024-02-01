public class Deadline extends Task {
    private String byWhen;

    public Deadline(String item, boolean marked, String byWhen) {
        super(item, marked);
        this.byWhen = byWhen;
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
        return "[D]" + m + " " + super.stringify() + " (by: " + this.byWhen + ")";
    }
}
