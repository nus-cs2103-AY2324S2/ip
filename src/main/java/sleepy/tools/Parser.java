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
     * @param input Input from the user to be parsed.
     * @return Parsed command, as an array of strings.
     * @throws IllegalArgumentException If the command is of an invalid form.
     */
    public static String[] parse(String input) throws IllegalArgumentException {
        String filteredInput = input.trim();
        switch (filteredInput.toLowerCase()) {
        case "bye":
            ResponseHandler.appendLineToResponse("Bye. Gonna go back to sleep now *yawn*");
            return new String[]{ "bye" };
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "delete":
            throw new IllegalArgumentException("You need to choose a task number to mark/unmark/delete!");
        case "find":
            throw new IllegalArgumentException("Your 'find' field cannot be empty!");
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "plan":
            // Fallthrough
        case "event":
            throw new IllegalArgumentException("You forgot to include the description of your task at all!");
        case "list":
            return new String[] { "list" };
        default:
            String[] splitInput = filteredInput.split(" ", 2);
            if (splitInput.length < 2) {
                throw new IllegalArgumentException("Invalid user input!");
            }
            String command = splitInput[0].trim().toLowerCase();
            String arguments = splitInput[1].trim();
            return evaluate(command, arguments);
        }
    }

    /**
     * Evaluates the user input, according to the command and other arguments.
     *
     * @param command Command type to be evaluated.
     * @param arguments Arguments passed to the command.
     * @return Identified and evaluated command, with the arguments parsed.
     * @throws IllegalArgumentException If the arguments are invalid for the command type.
     */
    public static String[] evaluate(String command, String arguments) throws IllegalArgumentException {
        switch (command) {
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "delete":
            // To detect if task number is invalid, will throw NumberFormatException otherwise
            Integer.parseInt(arguments);
            return new String[]{ command, arguments };
        case "find":
            return new String[]{ command, arguments };
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "plan":
            // Fallthrough
        case "event":
            return parseTask(command, arguments);
        default:
            throw new IllegalArgumentException("Invalid command type!");
        }
    }

    /**
     * Parses the given task.
     *
     * @param task Task of type ToDoTask, DeadlineTask or EventTask to be parsed, as a string.
     * @param details Details of the task.
     * @return Parsed task, as an array of strings.
     * @throws IllegalArgumentException If the string is of an invalid format.
     */
    public static String[] parseTask(String task, String details) throws IllegalArgumentException {
        switch (task) {
        case "todo":
            return parseTodo(details);
        case "deadline":
            return parseDeadline(details);
        case "plan":
            return parsePlan(details);
        case "event":
            return parseEvent(details);
        default:
            // Should never reach here as the evaluate function should have handled invalid tasks
            throw new IllegalArgumentException("Invalid task type!");
        }
    }

    /**
     * Parses the given toDo.
     *
     * @param toDoDetails Task of type ToDoTask, as a string.
     * @return Parsed toDo, as an array of strings.
     */
    public static String[] parseTodo(String toDoDetails) {
        return new String[]{ "todo", toDoDetails };
    }

    /**
     * Parses the given deadline.
     *
     * @param deadlineDetails Task of type DeadlineTask, as a string.
     * @return Parsed deadline, as an array of strings.
     * @throws IllegalArgumentException If the string is of an invalid format.
     */
    public static String[] parseDeadline(String deadlineDetails) throws IllegalArgumentException {
        String[] details = deadlineDetails.split("(?i)/by ");
        String deadlineDescription = details[0].trim();
        if (details.length == 1 || deadlineDescription.isEmpty()) {
            throw new IllegalArgumentException("Missing the deadline description or the '/by' field! Try again.");
        } else if (details.length >= 3) {
            throw new IllegalArgumentException("You can only have one '/by' field! Try again.");
        }
        String deadlineTiming = details[1].trim();
        if (deadlineTiming.isEmpty()) {
            throw new IllegalArgumentException("Your deadline timing is empty! Try again.");
        }
        return new String[]{ "deadline", deadlineDescription, deadlineTiming };
    }

    /**
     * Parses the given plan.
     *
     * @param planDetails Task of type PlannedTask, as a string.
     * @return Parsed plan, as an array of strings.
     * @throws IllegalArgumentException If the string is of an invalid format.
     */
    public static String[] parsePlan(String planDetails) throws IllegalArgumentException {
        String[] details = planDetails.split("(?i)/after ");
        String planDescription = details[0].trim();
        if (details.length == 1 || planDescription.isEmpty()) {
            throw new IllegalArgumentException("Missing the plan description or the '/after' field!"
                    + " Try again.");
        } else if (details.length >= 3) {
            throw new IllegalArgumentException("You can only have one '/after' field! Try again.");
        }
        String planTiming = details[1].trim();
        if (planTiming.isEmpty()) {
            throw new IllegalArgumentException("Your plan timing is empty! Try again.");
        }
        return new String[]{ "plan", planDescription, planTiming };
    }

    /**
     * Parses the given event.
     *
     * @param eventDetails Task of type EventTask, as a string.
     * @return Parsed event, as an array of strings.
     * @throws IllegalArgumentException If the string is of an invalid format.
     */
    public static String[] parseEvent(String eventDetails) throws IllegalArgumentException {
        String[] firstSplit = eventDetails.split("(?i)/from ");
        String eventDescription = firstSplit[0].trim();
        if (firstSplit.length == 1 || eventDescription.isEmpty()) {
            throw new IllegalArgumentException("Missing the event description or the '/from' field!"
                    + " Try again.");
        }
        if (firstSplit.length >= 3) {
            throw new IllegalArgumentException("You can only have one '/from' field! Try again.");
        }
        String[] secondSplit = firstSplit[1].split("(?i)/to ");
        if (secondSplit.length == 1) {
            throw new IllegalArgumentException("Your 'to' field is missing "
                    + "or before your 'from' field! Try again.");
        } else if (secondSplit.length >= 3) {
            throw new IllegalArgumentException("You can only have one '/to' field! Try again.");
        }
        String eventStartTiming = secondSplit[0].trim();
        String eventEndTiming = secondSplit[1].trim();
        if (eventStartTiming.isEmpty()) {
            throw new IllegalArgumentException("Your event start timing is empty! Try again.");
        } else if (eventEndTiming.isEmpty()) {
            throw new IllegalArgumentException("Your event end timing is empty! Try again.");
        }
        return new String[]{ "event", eventDescription, eventStartTiming, eventEndTiming };
    }
}
