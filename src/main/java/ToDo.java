public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo createToDo(String commandLine) throws CoDriverException {
        String[] arguments = commandLine.split(" ");
        if (arguments.length < 2) {
            throw new CoDriverException("Error! You cannot provide a todo with no description!");
        }
        return new ToDo(commandLine.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }
}
