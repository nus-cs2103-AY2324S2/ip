public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }

    public static ToDo fromString(String input) {
        String[] split = input.split(" \\| ");
        ToDo todo = new ToDo(split[2]);
        if (split[1].equals("X")) {
            todo.markAsDone();
        }
        return todo;
    }
}