package me.ruibin.leto.tasklist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** A type of Task representing a Todo */
public class Todo extends Task {

    private Todo(Boolean completed, String message) {
        super(completed, message);
    }

    /**
     * Create a instance of Todo from user command.
     *
     * @param input Command entered by the user, the entire line.
     * @return a new Todo object.
     * @throws TodoInvalidCmdException when input command is invalid.
     */
    public static Todo todoFromCmd(String input) throws TodoInvalidCmdException {
        String regex = "(?i)todo ([^,]+)";
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (!matcher.matches()) {
            throw new TodoInvalidCmdException();
        }
        String message = matcher.group(1);
        return new Todo(false, message);
    }

    /**
     * Create an Todo task from csv format:<br>
     * <code>Type,Completed,Task,By,From,To</code>.
     *
     * @param entry text string containing the row in the csv.
     * @return an Event task.
     */
    public static Todo todoFromCsv(String entry) throws InvalidTaskException {
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
     * Returns the object as a row in a csv table according to format: <br>
     * <code>Type,Completed,Task,By,From,To</code>.
     *
     * @return String in csv format
     */
    @Override
    public String toCsvString() {
        return "T," + super.toCsvString() + ",,,";
    }
}
