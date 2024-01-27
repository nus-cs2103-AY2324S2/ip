public class Event extends Task{

    private String from;
    private String to;
    private static String type_symbol = "[E]";

    public Event(String description, String from, String to){
        super(description, type_symbol);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return super.toString() + " (" + this.from + " - " + this.to + ")";
    }
}
