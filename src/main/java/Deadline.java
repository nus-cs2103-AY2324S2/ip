public class Deadline extends Task {
    String byWhen;
    public Deadline(String description, String byWhen, boolean done) {
        super(description, done);
        this.byWhen = byWhen;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), byWhen);
    }
}
