public class Deadline extends Task {
    private String end;

    public Deadline(String name, String end) {
        super(name);
        this.end = end;
    }

    @Override
    public String toString() {
        String str = super.toString();
        return "[D]" + str + " (by: " + this.end + ")";
    }
}
