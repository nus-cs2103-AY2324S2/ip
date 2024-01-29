public class ToDo extends Task {
    private static final String TASK_TYPE = "T";

    public ToDo(String title) {
        super(title);
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s,\"%s\"", TASK_TYPE, this.getIsMarked() ? "T" : "F", this.getTitle());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TASK_TYPE, super.toString());
    }
}