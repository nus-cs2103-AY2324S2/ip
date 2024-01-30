public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }

    public static Task fromFileFormat(String line) throws JayneException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 4 || !"D".equals(parts[0])) {
            throw new JayneException("Invalid line format for Deadline");
        }
        Deadline task = new Deadline(parts[2], parts[3]);
        if ("1".equals(parts[1])) {
            task.markAsDone();
        }
        return task;
    }

}
