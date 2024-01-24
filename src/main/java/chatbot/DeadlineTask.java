package chatbot;

import chatbot.exceptions.InvalidArgumentException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DeadlineTask extends Task {
    private String deadlineTime;

    public DeadlineTask(String desc, String deadlineTime) {
        super(desc);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadlineTime);
    }
}
