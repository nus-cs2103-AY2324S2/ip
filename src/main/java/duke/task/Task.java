package duke.task;

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
     * @param description the description of the task
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
     * @return the status description
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
     * @return description of the task
     */
    public String describe() {
        return description;
    }

    /**
     * Helper function to generate the expected set of {@link #validateComponentKeys}
     *
     * @param keys the expected components
     * @return the components as a set
     */
    protected static Set<String> keys(String... keys) {
        return new HashSet<>(List.of(keys));
    }

    /**
     * Used to validate the components of a command.
     * It checks whether the components are the same and that it has the default component (DESCRIPTION).
     *
     * @param expected the set of expected components
     * @param actual   the set of actual components
     * @throws InvalidComponents if expected != actual
     */
    protected static void validateComponentKeys(
            Set<String> expected, Set<String> actual) throws InvalidComponents {
        // DESCRIPTION is assumed to be implicit
        if (!actual.remove("DESCRIPTION")) {
            throw new InvalidComponents();
        }

        if (expected.size() != actual.size()) {
            throw new InvalidComponents(actual, expected);
        }

        for (String component : expected) {
            if (!actual.contains(component)) {
                throw new InvalidComponents(actual, expected);
            }
        }
    }

    /**
     * Parses the local date time of a task component. Right now the format is fixed to "dd/MM/yyyy HHmm"
     *
     * @param input string with a date and time in the given format.
     * @return the parsed LocalDateTime
     * @throws DateTimeParseException if the string doesn't match the pattern.
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
    }

    @Override
    public String toString() {
        return "[" + status.icon + "] " + description;
    }
}
