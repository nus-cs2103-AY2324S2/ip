package chatbot;

import chatbot.exceptions.InvalidArgumentException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventTask extends Task {
    private String startTime;
    private String endTime;

    public EventTask(String desc, String startTime, String endTime) {
        super(desc);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startTime, endTime);
    }
}

