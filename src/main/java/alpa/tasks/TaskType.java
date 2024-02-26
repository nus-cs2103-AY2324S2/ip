package alpa.tasks;

/**
 * Represents the type of a task.
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    /**
     * Returns the short name of the task type.
     *
     * @return the short name of the task type
     */
    public String getShortName() {
        switch (this) {
        case TODO:
            return "T";
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        default:
            return "Unknown";
        }
    }

    /**
     * Returns the TaskType enum value corresponding to the given short name.
     *
     * @param shortName the short name of the task type
     * @return the TaskType enum value corresponding to the given short name
     * @throws IllegalArgumentException if the short name is unknown
     */
    public static TaskType fromShortName(String shortName) {
        switch (shortName) {
        case "T":
            return TODO;
        case "D":
            return DEADLINE;
        case "E":
            return EVENT;
        default:
            throw new IllegalArgumentException("Unknown TaskType: " + shortName);
        }
    }
}
