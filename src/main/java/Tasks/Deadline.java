package Tasks;

import Exceptions.InvalidInputException;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.StringUtils.formatDateTime;
import static Utils.StringUtils.parseDateTime;

public class Deadline extends Task{
    LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
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
                LocalDateTime deadline= parseDateTime(matcher.group(2));
                return new Deadline(taskName, deadline);
            } else {
                throw new InvalidInputException("Invalid input for deadline. Input your deadline as such:\ndeadline <name_of_deadline> /by <due_date>");
            }
        } catch (Exception e){
            throw new InvalidInputException("Invalid input for deadline. Input your deadline as such:\ndeadline <name_of_deadline> /by <due_date>");
        }
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: "+ formatDateTime(this.by)+")";
    }

    public String save() {
        return "deadline " + super.description + " /by " + formatDateTime(this.by);
    }
}