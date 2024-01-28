package tasks;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String[] parts) {
        super(parts[0]);
        this.type = TaskType.EVENT;
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid input for event task. Expected: <description> /at <from> <to>");
        }
        this.from = parts[1];
        this.to = parts[2];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + from + " " + to + ")";
    }

    @Override
    public String formattedString() {
        return "E" + super.formattedString() + "|" + from + "|" + to;
    }

    @Override
    public String getType() {
        return type.toString();
    }
}
