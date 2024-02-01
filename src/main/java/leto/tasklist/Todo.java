package leto.tasklist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Todo extends Task {

    private Todo(Boolean completed, String message) { super(completed, message); }

    /**
     * Factory method for producing Todo objects
     * @param input Command entered by the user, the entire line
     * @return a new Todo object
     * @throws TodoInvalidCmdException when input command is invalid
     */
    public static Todo TodoFromCMD(String input) throws TodoInvalidCmdException {
        String regex = "(?i)todo ([^,]+)";
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (!matcher.matches()) {
            throw new TodoInvalidCmdException();
        }
        String message = matcher.group(1);
        return new Todo(false, message);
    }

    /**
     * Create an Todo task from csv format, Type,Completed,Task,By,From,To
     * @param entry text string containing the row in the csv
     * @return an Event task
     */
    public static Todo TodoFromCSV(String entry) throws InvalidTaskException {
        String regex = "([DTE]),([YN]),([^,]*),([^,]*),([^,]*),([^,]*)(\\n?)";
        Matcher matcher = Pattern.compile(regex).matcher(entry);
        if (!matcher.matches()) {
            throw new InvalidTaskException("Cannot match " + entry + " with regex");
        }
        Boolean completed = matcher.group(2).equals("Y");
        String message = matcher.group(3);
        return new Todo(completed, message);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the object as a row in a csv table according to format
     * Type,Completed,Task,By,From,To
     * @return String in csv format
     */
    @Override
    public String toCSVString() {
        return "T," + super.toCSVString() + ",,,";
    }
}
