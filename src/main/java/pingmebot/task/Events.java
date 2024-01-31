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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Events otherEvent = (Events) obj;
        return this.description.equals(otherEvent.description) && this.start.equals(otherEvent.start) && this.end.equals(otherEvent.end);
    }
}


