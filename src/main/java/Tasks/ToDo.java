package Tasks;

import Exceptions.InvalidFormatException;
import Exceptions.LeluException;

public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    public static ToDo ToDoOf(String input) throws InvalidFormatException {
        if (input.replaceAll(" ", "").equals("todo")) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.TODO);
        }
        String t = input.replaceFirst("todo ", "");
        return new ToDo(t);
    }
    public String saveFormat() {
        int check = this.completed ? 1 : 0;
        return String.format("T | %d | %s \n", check, this.taskName);
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
