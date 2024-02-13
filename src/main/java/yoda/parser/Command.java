package yoda.parser;
/**
 * Enum representing the various commands that the Yoda chatbot can understand.
 */
enum Command {
    BYE,     // Command to end the chat session
    LIST,    // Command to list all tasks
    MARK,    // Command to mark a task as done
    UNMARK,  // Command to mark a task as not done
    TODO,    // Command to add a new 'Todo' task
    DEADLINE, // Command to add a new 'Deadline' task
    DELETE,  // Command to delete a task
    EVENT,   // Command to add a new 'Event' task
    UNKNOWN, // Represents an unrecognized command
    SAVE, // Command to save the current task list to disk
    FIND; // Command to find tasks by searching for keywords


    /**
     * Converts a string to its corresponding Command enum value. If the string
     * does not match any command, UNKNOWN is returned.
     *
     * @param str The input string representing a command.
     * @return The corresponding Command enum value, or UNKNOWN if no match is found.
     */
    public static Command fromString(String str) {
        try {
            return Command.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN; // Return UNKNOWN if the input string is not a valid command
        }
    }
}
