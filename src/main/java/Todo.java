public class Todo extends Task {
    private String line = "____________________________________________________________";

    public Todo(String description) throws TodoException {
        super(description);
        if (description.isEmpty()) {
            throw new TodoException("\n" + line + "\nOpps!!! The description of a todo cannot be empty.\n" + line);
        }

    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + super.toString();
    }
}
