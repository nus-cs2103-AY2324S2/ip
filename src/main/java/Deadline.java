public class Deadline extends Task {
    String time;
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), time);
    }

    @Override
    public String toSavedString() {
        return String.format("D#!#%s#!#%s\n", super.toSavedString(), time);
    }
}
