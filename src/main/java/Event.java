public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String name, boolean isDone, String from, String to){
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    public String getTo(){
        return this.to;
    }

    public String getFrom() {
        return this.from;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from:" + from + " to:" + to + ")" ;
    }
}
