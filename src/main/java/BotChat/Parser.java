package BotChat;

/**
 * A utility class responsible for parsing user input and converting task information.
 */
public class Parser {

    private static final int TASK_TYPE_INDEX = 1;
    private static final int IS_DONE_INDEX = 5;
    private static final int DESCRIPTION_INDEX = 8;

    private static final String TODO_IDENTIFIER = "T";
    private static final String DEADLINE_IDENTIFIER = "D";
    private static final String EVENT_IDENTIFIER = "E";

    /**
     * Parses a string representation of a command and returns the corresponding Command enum.
     *
     * @param input The string representation of the command.
     * @return The Command enum associated with the input, or Command.UNKNOWN if not recognized.
     */
    public static Command getCommand(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    /**
     * Converts a string representation of a task into a Task object.
     *
     * @param line The string representation of a task.
     * @return The Task object created based on the input string, or null if parsing fails.
     */
    public static Task convertTask(String line) {
        String taskType = line.substring(TASK_TYPE_INDEX, TASK_TYPE_INDEX + 1);
        String description = line.substring(DESCRIPTION_INDEX);

        switch (taskType) {
        case TODO_IDENTIFIER:
            return new Todo(description);
        case DEADLINE_IDENTIFIER:
            return createDeadlineTask(description, line);
        case EVENT_IDENTIFIER:
            return createEventTask(description, line);
        default:
            return null;
        }
    }

    /**
     * Creates a Deadline task based on the provided description and line.
     *
     * @param description The description of the deadline task.
     * @param line        The string representation of the task.
     * @return The created Deadline task.
     */
    private static Task createDeadlineTask(String description, String line) {
        int byIndex = line.indexOf(" (by:");
        int endIndex = line.indexOf(")");
        int descriptionEnd = description.indexOf(" (by:");
        String taskDescription = description.substring(0, descriptionEnd);
        String by = line.substring(byIndex + 6, endIndex);
        Deadline deadlineTask = new Deadline(taskDescription, by);
        if (line.charAt(IS_DONE_INDEX) == 'X') {
            deadlineTask.mark();
        }
        return deadlineTask;
    }

    /**
     * Creates an Event task based on the provided description and line.
     *
     * @param description The description of the event task.
     * @param line        The string representation of the task.
     * @return The created Event task.
     */
    private static Task createEventTask(String description, String line) {
        int fromIndex = line.indexOf(" (from:");
        int toIndex = line.indexOf(" to: ");
        int endIndex = line.indexOf(")");
        int descriptionEnd = description.indexOf(" (from:");
        String taskDescription = description.substring(0, descriptionEnd);
        String from = line.substring(fromIndex + 8, toIndex);
        String to = line.substring(toIndex + 5, endIndex);
        Event eventTask = new Event(taskDescription, from, to);
        if (line.charAt(IS_DONE_INDEX) == 'X') {
            eventTask.mark();
        }
        return eventTask;
    }
}
