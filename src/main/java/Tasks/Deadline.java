package Tasks;

import Exceptions.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task{
    String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline deadlineFactory(String description) throws InvalidInputException {
        try {
            String regex = "deadline\\s+(\\S+(?:\\s+\\S+)*)\\s*/by\\s+(.+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(description);
            if (matcher.find()) {
                String taskName = matcher.group(1);
                String deadline = matcher.group(2);
                return new Deadline(taskName, deadline);
            } else {
                throw new InvalidInputException("Invalid input for deadline. Input your deadline as such:\ndeadline <name_of_deadline> /by <due_date>");
            }
        } catch (Exception e){
            throw new InvalidInputException("Invalid input for deadline. Input your deadline as such:\ndeadline <name_of_deadline> /by <due_date>");
        }
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: "+ this.by+")";
    }
}