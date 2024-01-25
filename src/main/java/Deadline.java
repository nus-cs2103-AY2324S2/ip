

public class Deadline extends Task {
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        String date = String.format(" (by: %s)", this.date);
        return "[D]" + super.toString() + date;
    }
}
