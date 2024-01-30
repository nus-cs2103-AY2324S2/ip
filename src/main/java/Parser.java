import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static Task parseTaskInput(String input) throws SolaireException {
        if (input.startsWith("deadline")) {
            Matcher deadlinePattern = Pattern.compile("^(?i)deadline\\s+(.+)\\s+/by\\s+(\\S+.*)").matcher(input);
            if (deadlinePattern.matches()) {
                String taskName = deadlinePattern.group(1);
                String deadline = deadlinePattern.group(2);

                return new Deadline(taskName, deadline);
            } else {
                throw new SolaireException(
                        "Incorrect format: follow deadline format as such: \n" + "deadline <description> /by <time>");
            }
        } else if (input.startsWith("todo")) {
            String[] inputTodo = input.split(" ", 2);
            if (inputTodo.length < 2 || inputTodo[1].trim().replaceAll("^\\s+", "").isEmpty()) {
                throw new SolaireException(
                        "The todo task description cannot be empty! Please use this format: \n" + "todo <description>");
            }
            return new Todo(inputTodo[1]);
        } else if (input.startsWith("event")) {
            Matcher eventPattern = Pattern.compile("^(?i)event\\s+(.+)\\s+/from\\s+(\\S+)\\s+/to\\s+(\\S+.*)$")
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
        } else {
            throw new SolaireException("Unable to determine task type");
        }
    }
}
