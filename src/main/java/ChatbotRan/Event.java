package ChatbotRan;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String contents, String from, String to) {
        super(contents);
        this.setFrom(from);
        this.setTo(to);
    }

    @Override
    String getType() {
        return "E";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    public static Event parse(String line, int space) {
        String[] texts = Util.parse(line, space, "/from", "/to");
        if (texts != null) {
            return new Event(texts[0], texts[1], texts[2]);
        }
        return null;
    }
}
