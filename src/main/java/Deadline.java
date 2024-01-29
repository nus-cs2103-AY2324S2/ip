public class Deadline extends Task {
    private static final String TASK_TYPE = "D";
    private String by;

    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s,%s,%s", TASK_TYPE, this.getIsMarked() ? "T" : "F", this.getTitle(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (By: %s)", TASK_TYPE, super.toString(), this.by);
    }
}
