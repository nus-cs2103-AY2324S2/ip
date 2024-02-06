package Tasks;

import Exceptions.InvalidFormatException;
import Exceptions.LeluException;

/**
 * This class represents a ToDo task by encapsulating information about a specific task,
 * including the description of the task.
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * A factory method which creates a ToDo object using the user input.
     *
     * @param input User input which starts with "ToDo".
     * @return A ToDo object with the specified name.
     * @throws InvalidFormatException When the description of task is empty in the user input.
     */
    public static ToDo ToDoOf(String input) throws InvalidFormatException {
        if (input.replaceAll(" ", "").equals("todo")) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.TODO);
        }
        String t = input.replaceFirst("todo ", "");
        return new ToDo(t);
    }

    /**
     * Formats the details of the ToDo object as a String to be written to a text file.
     *
     * @return A String containing the description of the ToDo object.
     */
    public String saveFormat() {
        int check = this.completed ? 1 : 0;
        return String.format("T | %d | %s \n", check, this.taskName);
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
