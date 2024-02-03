public class Deadline extends Task{
    public String dueDate;

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String print() {
        String str = "[D]" + super.print() + "(by: " +
                this.dueDate + ")";
        return str;
    }
    @Override
    public String getDescription() {
        String str = "[E] " + super.getDescription() + " " + this.dueDate;
        return str;
    }
}
