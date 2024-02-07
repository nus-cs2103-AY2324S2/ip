public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws InvalidInputException{
        super(description);
        if (description.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The description of a deadline cannot be empty.");
        }
        else if(by.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The deadline of a deadline cannot be empty.");
        }
        else {
            this.by = cleanWhiteSpace(by);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
