package cappy.constant;

/** Represents the error messages that can be shown to the user. */
public class Message {
    public static final String MISSING_DESCRIPTION = "Please enter the task description";
    public static final String MISSING_DUE_DATE =
            "Please specify the due date of the deadline task using /by [Date Time].";
    public static final String MISSING_TO_FROM_DATETIME =
            "Please specify the duration of the event using /from [Date Time] /to [Date Time].";
    public static final String MISSING_KEYWORD = "Please enter a keyword to search for.";
    public static final String MISSING_INDEX = "Please enter an index.";

    public static final String INVALID_DATETIME_FORMAT = "Please use the correct datetime format.";
    public static final String INVALID_INDEX = "Please enter a valid index.";
    public static final String INVALID_COMMAND = "I'm sorry but I don't know what that means :-(";
    public static final String INVALID_STORAGE_FORMAT = "Invalid storage format!";

    public static final String NO_MATCHES_FOUND = "No matches found :(";

    public static final String GREETING =
            "Hello! I'm Cappy\n"
                    + "What can I do for you?\n"
                    + "(Type `help` for list of commands and how to use them)";
    public static final String GOODBYE = "Bye! Hope to see you again soon!";
    public static final String HELP =
            "Cappy to the rescue! Here are the available commands:\n\n"
                + "- Add a Todo Task:\n"
                + "   - Syntax: `todo <task_description>`\n"
                + "   Example: `todo Finish math assignment`\n\n"
                + "- Add a Deadline Task:\n"
                + "   - Syntax: `deadline <task_description> /by <DateTime>`\n"
                + "   - DateTime format: YYYY-MM-DD'T'HH:MM\n"
                + "   Example: `deadline Finish math assignment /by 2024-02-14T14:59`\n\n"
                + "- Add an Event Task:\n"
                + "   - Syntax: `event <task_description> /from <DateTime> /to <DateTime>`\n"
                + "   - DateTime format: YYYY-MM-DD'T'HH:MM\n"
                + "   Example: `event Math Exam /from 2024-02-14T15:00 /to 2024-02-14T17:00`\n\n"
                + "- List all Tasks:\n"
                + "   - Command: `list`\n\n"
                + "- Delete a Task:\n"
                + "   - Syntax: `delete <task_id>`\n"
                + "   Example: `delete 2`\n\n"
                + "- Mark Task as Completed:\n"
                + "   - Syntax: `mark <task_id>`\n"
                + "   Example: `mark 1`\n\n"
                + "- Mark Task as not completed:\n"
                + "   - Syntax: `unmark <task_id>`\n"
                + "   Example: `unmark 1`\n\n"
                + "- Find a Task containing a given keyword:\n"
                + "   - Syntax: `find <keyword>`\n"
                + "   Example: `find math`\n\n"
                + "- Exit Application:\n"
                + "   - Syntax: `bye`\n\n"
                + "- Show Help:\n"
                + "   - Syntax: `help`\n\n"
                + "Feel free to use these commands to manage your tasks efficiently! If you have"
                + " any questions, type `help` to see this message again.";
}
