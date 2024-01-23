public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo parseCommand(String command) throws CoDriverException {
        String[] words = command.split(" ");
        if (!words[0].equals("todo")) {
            throw new CoDriverException("I'm sorry, I don't understand this command: " + words[0]);
        }
        if (words.length < 2) {
            throw new CoDriverException("Error! You cannot provide a todo with no description!");
        }
        return new ToDo(command.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
