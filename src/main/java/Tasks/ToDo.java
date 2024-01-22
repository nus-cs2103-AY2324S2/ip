package Tasks;

import Exceptions.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public static ToDo ToDoFactory(String description) throws InvalidInputException {
        try {
            String regex = "todo\\s+(.+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(description);
            if (matcher.find()) {
                String taskName = matcher.group(1);
                return new ToDo(taskName);
            } else {
                throw new InvalidInputException("Invalid input");
            }
        } catch (Exception e){
            throw new InvalidInputException("Invalid input");
        }
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}