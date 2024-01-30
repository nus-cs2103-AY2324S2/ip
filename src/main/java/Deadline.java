public class Deadline extends Task {
    private String time;

    public Deadline(boolean done, String name, String time) {
        super(done, name);
        this.time = time;
    }

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toSavedString() {
        return String.format("D,%s,%s,%s", this.done ? '1' : '0', this.name, this.time);
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)\n", this.done ? "X" : " ", this.name, this.time);
    }
}
