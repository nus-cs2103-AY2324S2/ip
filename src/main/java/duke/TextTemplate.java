package duke;

/**
 * The TextTemplate class provides predefined string templates used for generating standard messages
 * in the Duke application.
 */
public class TextTemplate {
    public static final String LINE_BREAK = "________________________";
    public static final String GREETING = "Hello! I'm Beepo.\nWhat can I do for you?";
    public static final String EXIT = "Bye. Hope to see you again soon!";
    public static final String ADD_TASK = "Got it. I've added this task:";
    public static final String TASK_COUNT = "Now you have %d tasks in the list.";
    public static final String MARK_TASK = "Nice! I've marked this task as done:";
    public static final String UNMARK_TASK = "Nice! I've unmarked this task:";
    public static final String TODO_NO_DESC = "OOPS!!! The description of a todo cannot be empty.";
    public static final String DELETE_TASK = "Noted. I've removed this task:";
    public static final String TASK_DOES_NOT_EXIST = "OOPS!!! This task does not exist.";
    public static final String INVALID_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String INVALID_DATETIME = "OOPS!!! The date format is invalid. Either enter "
            + "yyyy-mm-dd or yyyy-mm-dd HHmm :-(";
    public static final String INVALID_MARK = "OOPS!!! This task cannot be marked yet :-(";
}
