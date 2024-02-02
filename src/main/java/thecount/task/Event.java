package thecount.task;

import thecount.ui.AddToListReply;
import thecount.ui.Reply;

public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event (String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void displayMessage(int currSize) {
        Reply replyToUser = new AddToListReply(this.toString(), currSize);
        replyToUser.displayMessage();
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " | " + this.startTime + "-" + this.endTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime
                + " to: " + this.endTime + ")";
    }
}
