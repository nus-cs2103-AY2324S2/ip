public class Todo extends Task {
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    public static Todo todoParse(boolean isDone, String input) throws TaskCreationException {
        String description = input.split(" ", 2)[1];
        if (description.equals("")) {
            throw new TaskCreationException("Missing Information: \"description\"" );
        }

        Todo t = new Todo(false, description);
        return t;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "[T]|" + super.toSave();
    }
}
