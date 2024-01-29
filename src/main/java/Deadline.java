class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String toFileFormat() {
        String completed = this.isDone ? "1" : "0";
        return "D | " + completed + " | " + this.description + " | " + this.by;
    }
}