public class ToDo extends Task{
    public ToDo(String description) throws InvalidInputException {
        super(description);
        if (description.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
