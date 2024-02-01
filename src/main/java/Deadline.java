public class Deadline extends Task {
    private String deadlineTime;

    public Deadline (String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    public void displayMessage(int currSize) {
        Reply replyToUser = new AddToListReply(this.toString(), currSize);
        replyToUser.displayMessage();
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " | " + this.deadlineTime;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineTime + ")";
    }
}
