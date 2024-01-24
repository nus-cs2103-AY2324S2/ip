public class Events extends Task{
    private String from;
    private String to;
    public Events(String event, String from, String to) {
        super(event);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTag() {
        return "[E]";
    }
}
