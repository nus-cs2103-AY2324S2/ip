public class DeadlineTask extends Task {
    private String type;
    private String deadline;
    public DeadlineTask(String what, String deadline) {
        super(what);
        this.type = "[D]";
        this.deadline = deadline;
    }
    public String showAll() {
        return this.type + super.showAll() + "(by: " + this.deadline + ")";
    }
}
