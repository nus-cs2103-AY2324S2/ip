package solaire.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import solaire.data.exception.SolaireException;
import solaire.data.task.Deadline;
import solaire.data.task.Event;
import solaire.data.task.Task;
import solaire.data.task.Todo;

/**
 * Represents a utility class Parser.
 * Given a string user input, parses the given input and returns a corresponding <code>Task</code> object
 */
public class Parser {

    /**
     * Parses a user input string and returns a corresponding <code>Task</code> object.
     *
     * @param input user input as String.
     * @return Task a <code>Task</code> object corresponding to the parsed input.
     * @throws SolaireException if input fails to pattern-match with given patterns.
     */
    public static Task parseTaskInput(String input) throws SolaireException {
        if (input.startsWith("deadline")) {
            return getDeadline(input);
        } else if (input.startsWith("todo")) {
            return getTodo(input);
        } else if (input.startsWith("event")) {
            return getEvent(input);
        } else {
            throw new SolaireException("Unable to determine task type");
        }
    }

    private static Event getEvent(String input) throws SolaireException {
        Matcher eventPattern = Pattern.compile("^(?i)event\\s+(.+)\\s+/from\\s+(\\S+.*)\\s+/to\\s+(\\S+.*)$")
                .matcher(input);

        if (eventPattern.matches()) {
            String taskName = eventPattern.group(1);
            String from = eventPattern.group(2);
            String to = eventPattern.group(3);

            return new Event(taskName, from, to);
        } else {
            throw new SolaireException("Incorrect format; follow event format as such:\n"
                    + "event <description> /from <start> /to <end>");
        }
    }

    private static Todo getTodo(String input) throws SolaireException {
        String[] inputTodo = input.split(" ", 2);
        if (inputTodo.length < 2 || inputTodo[1].trim().replaceAll("^\\s+", "").isEmpty()) {
            throw new SolaireException(
                    "The todo task description cannot be empty! Please use this format: \n" + "todo <description>");
        }
        return new Todo(inputTodo[1]);
    }

    private static Deadline getDeadline(String input) throws SolaireException {
        Matcher deadlinePattern = Pattern.compile("^(?i)deadline\\s+(.+)\\s+/by\\s+(\\S+.*)").matcher(input);
        if (deadlinePattern.matches()) {
            String taskName = deadlinePattern.group(1);
            String deadline = deadlinePattern.group(2);

            return new Deadline(taskName, deadline);
        } else {
            throw new SolaireException(
                    "Incorrect format: follow deadline format as such: \n" + "deadline <description> /by <time>");
        }
    }
}
