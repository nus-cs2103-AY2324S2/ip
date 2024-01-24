public class Deadline extends Task {
    Deadline(String text, TaskStatus status) {
        super(text, status);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString();
    }
}
