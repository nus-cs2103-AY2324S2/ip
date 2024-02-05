public class Deadline extends Task {
    protected String end;

    public Deadline(String name, String end) {
        super(name);
        this.end = end;
    }

    public Deadline(String name, String end, boolean isDone) {
        super(name);
        this.end = end;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String str = super.toString();
        return "[D]" + str + " (by: " + this.end + ")";
    }
}
