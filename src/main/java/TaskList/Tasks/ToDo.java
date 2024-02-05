package TaskList.Tasks;

import Exceptions.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a todo task.
 * A <code>ToDo</code> object corresponds to a task with a description
 * e.g., <code>"todo read book"</code>
 */
public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String save() {
        return "todo " + super.description;
    }
}