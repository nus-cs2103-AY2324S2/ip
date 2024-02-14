package atsisbot.task;

/**
 * The Priority enumeration represents the priority level of a task.
 * It can be HIGH, MEDIUM, or LOW.
 */
public enum Priority {
    HIGH,
    MEDIUM,
    LOW;

    /**
     * This method overrides the default toString method for the Priority enum.
     * It returns the name of the enum constant, converted to lower case.
     *
     * @return A string representation of the enum constant, in lower case.
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
