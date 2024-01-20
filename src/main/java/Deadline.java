public class Deadline extends Task {
    public Deadline(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
