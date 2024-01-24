public class Event extends Task{

    private String from;
    private String to;
    public Event(String name, String from, String to){
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        String tag = super.getTag() ? "[X]" : "[ ]";
        return "[E]" + tag + " " + super.getName().strip() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
