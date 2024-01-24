public class Deadline extends Task {
    private String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)\n", this.done ? "X" : " ", this.name, this.time);
    }
}
