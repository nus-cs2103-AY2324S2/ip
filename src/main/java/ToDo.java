public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo parseCommand(String command) {
        return new ToDo(command.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
