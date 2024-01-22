public class Event extends Task{

    public String from;
    public String to;
    public Event(String name, String from, String to){
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        String tag = super.getTag() ? "[X]" : "[ ]";
        return "[E]" + tag + " " + super.getName() + "(from: " + this.from + " to: " + this.to + ")";
    }
}
