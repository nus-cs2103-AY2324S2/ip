package dave.common;

public class Messages {
    /**
     * The feedback to show to user when given an invalid keyword for the FIND
     * command.
     */
    public static final String INVALID_KEYWORD_MESSAGE = "Dave cannot find a task without a keyword."
            + "\nPlease help Dave by entering your search as follows:\n"
            + "\nfind <keyword>";

    /**
     * The feedback to show to user when given an invalid task name for the addTodo
     * command.
     */
    public static final String INVALID_TODO_NAME = "Dave cannot record a TODO task without a name."
            + "\nPlease help Dave by entering your TODO name as follows:\n"
            + "\ntodo <name>";

    /**
     * The feedback to show to user when given an invalid task name or deadline for
     * the addDeadline command.
     */
    public static final String INVALID_DEADLINE_NAME_OR_DEADLINE = "Dave cannot record a DEADLINE task"
            + " without a name/deadline."
            + "\nPlease help Dave by entering your DEADLINE task as follows:\n"
            + "\ndeadline <name> /by dd-mm-yyyy hhmm";

    /**
     * The feedback to show to user when given an invalid event name or duration for
     * the addEvent command.
     */
    public static final String INVALID_EVENT_NAME_OR_DURATION = "Dave cannot record an EVENT task"
            + " without a name/duration."
            + "\nPlease help Dave by entering your EVENT task as follows:\n"
            + "\nevent <name> /from dd-mm-yyy hhmm /to dd-mm-yyyy hhmm";
}
