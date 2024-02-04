public class Deadline extends Task{
    private String dueDate;
    private String type;

    public Deadline(String description) {
        super(description);
        this.type = "D";
    }

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
        this.type = "D";
    }

    @Override
    public String print() {
        String str = "[D]" + super.print() + "(by: " +
                this.dueDate + ")";
        return str;
    }
    @Override
    public String getDescription() {
        String str = "[D] " + super.getDescription() + " " + this.dueDate;
        return str;
    }

    @Override
    public String getTaskInfo() {
        return "[D] " + "/ [" + super.getStatusIcon()
                + "] / " + super.getTaskInfo() + " / " + this.dueDate;
    }
}
