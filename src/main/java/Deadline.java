public class Deadline extends Task {
    protected String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    private String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + taskName + " | " + by;
    }

    public static Deadline fromFileFormat(String fileFormat) throws TaskException {
        String[] parts = fileFormat.split(" \\| ");
        if (parts.length < 3) {
            throw TaskException.forInvalidTaskFormat("ToDo");
        }
        Deadline deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("1")) {
            deadline.check();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() + " " + taskName + " (by: " + by + ")";
    }
}
