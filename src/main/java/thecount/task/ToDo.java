package thecount.task;

import thecount.ui.AddToListReply;
import thecount.ui.Reply;

public class ToDo extends Task {
    public ToDo (String description) {
        super(description);
    }

    public void displayMessage(int currSize) {
        Reply replyToUser = new AddToListReply(this.toString(), currSize);
        replyToUser.displayMessage();
    }

    @Override
    public String getType() {
        return "T";
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
