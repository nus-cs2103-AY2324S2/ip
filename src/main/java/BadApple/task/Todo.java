package BadApple.task;

import BadApple.main.BadAppleException;

import java.util.ArrayList;

/**
 * Todo shares the same features as its parent class Task
 * with the added feature of being able to extract its own
 * description for the Task. It rejects empty task names.
 */
public class Todo extends Task {

    protected Todo(String desc) {
        super(desc);
    }

    /**
     * This is a Factory Method that generates a Todo instance
     * @param s an ArrayList after tokenizing the query.
     */
    public static Todo extractDetails(String s) throws BadAppleException {
        if (s.split(" ").length == 1) {
            throw new BadAppleException("There is nothing here.");
        }
        // This removes the "todo" from the front
        return new Todo(s.substring(5));
    }
    /**
     * Creates a Command when a Task parsed from WHITESPACE is of
     * the Todo type.
     * @param args Processed List of arguments
     * @return A generic Command which will be processed to add a Todo when executed.
     */
    public static Command parseTodoFromFile(ArrayList<String> args) {
        // the fourth token should be the task name for this command.
        if (args.size() < 4) {
            throw new BadAppleException("Todo Task in wrong format, " +
                    "should be <number> todo <status> <taskName>");
        }
        StringBuilder taskName = new StringBuilder();
        for (int i = 3; i < args.size(); i++) {
            taskName.append(args.get(i));
        }

        String query = "todo " + taskName;

        return new Command("todo", query);
    }

    @Override
    public String toString() {
        return "Todo" + " " + super.toString();
    }
}
