public class ToDo extends Task {
    public ToDo(String description) throws DukeException {
        super(description);
        if (description.trim().length() == 0) {
            throw new DukeException("Please enter a description for this todo task");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
