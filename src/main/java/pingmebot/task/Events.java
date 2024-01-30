package pingmebot.task;

public class Events extends Task {
    protected String start;
    protected String end;
    protected String description;
    public Events(String description, String start, String end) {
        super(description);
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + start + " to:" + end + ")";
    }

    public String updateEventText(int isCompleted) {
        String text = "";
        text += "event | " + isCompleted + " | " + this.description + " | " + this.start + " | " + this.end;
        return text;
    }
}


