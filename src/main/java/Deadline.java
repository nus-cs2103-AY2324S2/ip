public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + by;
    }

    public static Deadline fromString(String input) {
        String[] split = input.split(" \\| ");
        Deadline deadline = new Deadline(split[2], split[3]);
        if (split[1].equals("X")) {
            deadline.markAsDone();
        }
        return deadline;
    }
}