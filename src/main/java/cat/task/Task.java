package cat.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents one task. The task can either be completed or pending.
 */
public abstract class Task implements Serializable {
    protected static DateTimeFormatter describeTimeFormat =
            DateTimeFormatter.ofPattern("EE',' dd MMMM yy 'at' hh:mma");

    private final String description;
    private Status status;

    /**
     * The completion status of the task.
     */
    private enum Status {
        Complete("completed", 'X'), Incomplete("pending", ' ');

        private final char icon;
        private final String description;

        Status(String description, char icon) {
            this.icon = icon;
            this.description = description;
        }
    }

    /**
     * The constructor of a task.
     *
     * @param description The description of the task.
     */
    protected Task(String description) {
        assert description != null : "Description must not be null";

        this.description = description;
        status = Status.Incomplete;
    }

    /**
     * Sets the task to complete.
     */
    public final void setStatus(boolean isComplete) {
        if (isComplete) {
            status = Status.Complete;
        } else {
            status = Status.Incomplete;
        }
    }

    /**
     * Returns a description of the status of the Task
     *
     * @return The status description.
     */
    public final String status() {
        return status.description;
    }

    /**
     * Checks if the given query string is in the task description.
     */
    public final boolean inDescription(String query) {
        return description.contains(query);
    }

    /**
     * A more verbose english explanation of the task.
     *
     * @return The description of the task.
     */
    public String describe() {
        return description;
    }

    /**
     * Helper function to generate the expected set of {@link #validateComponentKeys}
     *
     * @param keys The expected components.
     * @return The components as a set.
     */
    protected static Set<String> keys(String... keys) {
        return new HashSet<>(List.of(keys));
    }

    /**
     * Used to validate the components of a command.
     * It checks whether the components are the same and that it has the default component (DESCRIPTION).
     *
     * @param expected The set of expected components.
     * @param actual   The set of actual components.
     * @throws InvalidComponents If expected != actual.
     */
    protected static void validateComponentKeys(
            Set<String> expected, Set<String> actual) throws InvalidComponents {
        if (!expected.equals(actual)) {
            throw new InvalidComponents(actual, expected);
        }
    }

    /**
     * Parses the local date time of a task component. Right now the format is fixed to "dd/MM/yyyy HHmm"
     *
     * @param input A string with a date and time in the given format.
     * @return The parsed LocalDateTime.
     * @throws DateTimeParseException If the string doesn't match the pattern.
     */
    protected static LocalDateTime parseDateTime(String input) throws InvalidComponents {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.from(f.parse(input));
        } catch (DateTimeParseException e) {
            throw new InvalidComponents(input);
        }
    }

    /**
     * Error when the components parsed don't match the expected components.
     * This includes checking the default parameter (called DESCRIPTION).
     */
    public static class InvalidComponents extends Exception {
        public InvalidComponents(Set<String> actual, Set<String> expected) {
            super("Invalid task components: " + actual + "; expected: " + expected);
        }

        public InvalidComponents() {
            super("No description given");
        }

        public InvalidComponents(String date) {
            super("Value cannot be parsed to date and time: " + date);
        }

        /**
         * The invalid component when the time range is invalid.
         * @param from The start time.
         * @param to The end time.
         */
        public InvalidComponents(LocalDateTime from, LocalDateTime to) {
            super("Date and time for /from (" + from.format(describeTimeFormat)
                    + ") is greater than /to (" + to.format(describeTimeFormat) + ")");
        }
    }

    @Override
    public String toString() {
        return "[" + status.icon + "] " + description;
    }
}
