package Tasks;

import Exceptions.InvalidFormatException;

public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    public static ToDo ToDoOf(String input) throws InvalidFormatException {
        if (input.replaceAll(" ", "").equals("todo")) {
            InvalidFormatException.callInvalidFormatException("todo");
        }
        String t = input.replaceFirst("todo ", "");
        return new ToDo(t);
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
