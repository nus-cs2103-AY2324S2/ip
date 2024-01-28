public class Event extends Task{

    private String from;

    private String to;
    public Event(String content,String from,String to) {
        super(content);
        this.from = from;
        this.to = to;
    }

    public String getType(){
        return "E";
    }

    public String getStart(){
        return this.from;
    }

    public String getEnd(){
        return this.to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
