public class Todo extends Task {
    /**
     * Constructs a new Todo object with the specified description.
     *
     * @param description a String representing the task description
     */
    public Todo(String description) {
        super (description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getData() {
        return "T | " + super.getData();
    }
}
