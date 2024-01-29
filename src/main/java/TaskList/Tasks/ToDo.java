package TaskList.Tasks;

import Exceptions.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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