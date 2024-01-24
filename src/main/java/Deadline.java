public class Deadline extends Task {
    private String to;

    public Deadline(String name, String to) {
        super(name);
        this.to = to;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.to + ")";
    }
}
