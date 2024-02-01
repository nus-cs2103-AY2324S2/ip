package sleepy.tools;

/**
 * This class helps to parse comments the user enters.
 *
 * @author kjw142857
 */
public class Parser {

    /**
     * Parses the given command.
     *
     * @param command Command from the user to be parsed.
     *
     * @return Parsed command, as an array of strings.
     */
    public static String[] parse(String command) throws IllegalArgumentException {
        switch (command) {
        case "mark", "unmark", "delete":
            throw new IllegalArgumentException("You need to choose a task number to mark/unmark/delete!");
        case "find":
            throw new IllegalArgumentException("Your 'find' field cannot be empty!");
        case "todo", "deadline", "event":
            throw new IllegalArgumentException("You forgot to include the description of your task at all!");
        case "list":
            return new String[]{"list"};
        default:
            if (command.startsWith("mark ")) {
                return new String[]{ "mark", command.substring(5) };
            } else if (command.startsWith("unmark ")) {
                return new String[]{ "unmark", command.substring(7) };
            } else if (command.startsWith("delete ")) {
                return new String[]{"delete", command.substring(7)};
            } else if (command.startsWith("find ")) {
                return new String[]{"find", command.substring(5)};
            } else {
                return new String[]{"add", command};
            }
        }
    }

    /**
     * Parses the given task.
     *
     * @param task Task of type ToDo, Deadline or Event to be parsed.
     *
     * @return Parsed task, as an array of strings.
     */
    public static String[] parseTask(String task) throws IllegalArgumentException {
        boolean isToDo = task.startsWith("todo ");
        boolean isDeadline = task.startsWith("deadline ");
        boolean isEvent = task.startsWith("event ");
        if (isToDo) {
            return new String[]{ "todo", task.substring(5) };
        } else if (isDeadline) {
            String[] details = task.substring(9).split(" /by ");
            if (details.length == 1) {
                throw new IllegalArgumentException("Missing the task description or the '/by' field! Try again.");
            } else if (details.length >= 3) {
                throw new IllegalArgumentException("You can only have one '/by' field! Try again.");
            } else {
                return new String[]{ "deadline", details[0], details[1] };
            }
        } else if (isEvent) {
            String[] firstSplit = task.substring(6).split(" /from ");
            if (firstSplit.length == 1) {
                throw new IllegalArgumentException("Missing the task description or the '/from' field! Try again.");
            } else if (firstSplit.length >= 3) {
                throw new IllegalArgumentException("You can only have one '/from' field! Try again.");
            }
            String[] secondSplit = firstSplit[1].split(" /to ");
            if (secondSplit.length == 1) {
                throw new IllegalArgumentException("Your 'to' field is missing " +
                        "or before your 'from' field! Try again.");
            } else if (secondSplit.length >= 3) {
                throw new IllegalArgumentException("You can only have one '/to' field! Try again.");
            } else {
                return new String[]{ "event", firstSplit[0], secondSplit[0], secondSplit[1] };
            }
        } else {
            throw new IllegalArgumentException("This is not a task!");
        }
    }
}
