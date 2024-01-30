public class Event extends Task{

    private String from;
    private String to;
    private static String type_symbol = "[E]";

    public Event(String description, String from, String to) throws MeanDukeException{
        super(description, type_symbol);
        if(from.isEmpty() || to.isEmpty()){
            throw new MeanDukeException("Usage: \"add event <description> /from <start> /to <end>\"");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return super.toString() + " (" + this.from + " - " + this.to + ")";
    }
}
