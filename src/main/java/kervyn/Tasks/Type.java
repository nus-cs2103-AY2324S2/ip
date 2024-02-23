package kervyn.Tasks;

/**
 * Enumerates the different types of tasks available in the application.
 * Includes TODO, DEADLINE, and EVENT as the possible types.
 */
public enum Type {
        /** Represents a ToDo type task. */
        TODO,

        /** Represents a Deadline type task with a specific due date. */
        DEADLINE,

        /** Represents an Event type task with a start and end date. */
        EVENT
}