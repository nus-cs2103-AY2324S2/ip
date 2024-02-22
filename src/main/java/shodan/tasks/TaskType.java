package shodan.tasks;

/**
 * Represents the supported tasks types.
 */
public enum TaskType {
    /**
     * A task that only has a name.
     */
    TODO,
    /**
     * A task with an ending date.
     */
    DEADLINE,
    /**
     * A task with a start and ending date.
     */
    EVENT;
}
