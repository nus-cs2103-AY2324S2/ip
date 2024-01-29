package TaskList.Tasks;

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

    public String toString() {
        return "[D]" + super.toString() + " (by: "+ formatDateTime(this.by)+")";
    }

    public String save() {
        return "deadline " + super.description + " /by " + formatDateTime(this.by);
    }
}