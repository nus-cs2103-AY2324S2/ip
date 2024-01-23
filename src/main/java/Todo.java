public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public static Todo fromStr(String input) throws MissMinutesException {
        if (input.isEmpty()) {
            throw new MissMinutesException("Todos have to be created with the following format: todo <desc>");
        } else {
            return new Todo(input);
        }
    }

    @Override
    public String toString() {
        return  "[T]" + super.toString();
    }
}
