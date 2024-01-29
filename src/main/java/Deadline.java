public class Deadline extends Task {
    String label = "[D]";

    public Deadline(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return label + super.toString() + " ";
    }
}
