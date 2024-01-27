package chatbot;

import java.io.Serializable;

public class EventTask extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
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

