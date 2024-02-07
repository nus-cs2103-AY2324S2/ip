public class Event extends Task {
    protected String from;
    protected String to;


    public Event(String description, String from, String to) throws InvalidInputException{
        super(description);
        if (description.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The description of a event cannot be empty.");
        }
        else if(from.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The start time of a event cannot be empty.");
        }
        else if (to.isEmpty()){
            throw new InvalidInputException("OOPS!!! The end time of a event cannot be empty.");
        }
        else {
            this.from = cleanWhiteSpace(from);
            this.to = cleanWhiteSpace(to);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
