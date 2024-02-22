public class Deadlines extends Task{
    protected String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public void print() {
        System.out.println("\t" + "[D]" + getStatusIcon() + getDescription() + "(by: " + deadline + ")");
    }
}
