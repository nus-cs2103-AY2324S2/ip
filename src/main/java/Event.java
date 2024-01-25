public class Event extends Task{
    private String from;
    private String to;

    public Event(String input) {
        //"project meeting /from Mon 2pm /to 4pm"
        super(input.split("/")[0].strip());
        String[] tokens = input.split("/");
        //"from Mon 2PM"
        this.from = tokens[1].split("from")[1].strip();
        this.to = tokens[2].split("to")[1].strip();
    }

    @Override
    public String toString() {
        if (super.done){
            return "[E][X] " + super.taskContent + String.format("(from: %s to: %s)", this.from, this.to);
        }
        return "[E][ ] " + super.taskContent + String.format("(from: %s to: %s)", this.from, this.to);
    }
}
