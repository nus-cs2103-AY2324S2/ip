package cruisey.task;

/**
 * The enumeration {@code TaskPriority} represents the priority levels that can be associated with tasks.
 * Priority can be set to HIGH, MEDIUM, or LOW.
 *
 * @see cruisey.task.Task
 */
public enum TaskPriority {

    /**
     * High priority level.
     */
    HIGH,

    /**
     * Medium priority level.
     */
    MEDIUM,

    /**
     * Low priority level.
     */
    LOW;

    /**
     * The priority marker used to indicate the presence of a priority level in a command.
     * Example: "/p HIGH" sets the priority of a task to HIGH.
     */
    public static final String PRIORITY_MARKER = "/p";
}
