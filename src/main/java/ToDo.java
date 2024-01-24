public class ToDo extends Task {
    public ToDo (String description) {
        super(description);
    }

    public void displayMessage(int currSize) {
        Reply replyToUser = new AddToListReply(this.toString(), currSize);
        replyToUser.displayMessage();
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
