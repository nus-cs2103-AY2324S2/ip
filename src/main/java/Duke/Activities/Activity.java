package Duke.Activities;

import java.time.LocalDate;

/**
 * Represents an abstract class for various activities, such as Todos, Deadlines, and Events.
 */
public abstract class Activity {

    /**
     * The name of the activity.
     */
    private final String name;

    /**
     * Indicates whether the activity is marked as completed.
     */
    boolean isMarked;

    /**
     * Constructor to initialize an activity with a specified name.
     *
     * @param name The name of the activity.
     */
    public Activity(String name) {
        this.name = name;
        this.isMarked = false;
    }

    /**
     * Factory method to create a new Todo activity.
     *
     * @param name The name of the Todo activity.
     * @return A new Todo activity instance.
     */
    public static Activity of(String name) {
        return new Todo(name);
    }

    /**
     * Factory method to create a new Deadline activity with a specified date.
     *
     * @param name The name of the Deadline activity.
     * @param date The deadline date.
     * @return A new Deadline activity instance.
     */
    public static Activity of(String name, LocalDate date) {
        return new Deadline(name, date);
    }

    /**
     * Factory method to create a new Event activity with specified start and end dates.
     *
     * @param name  The name of the Event activity.
     * @param start The start date of the event.
     * @param end   The end date of the event.
     * @return A new Event activity instance.
     */
    public static Activity of(String name, LocalDate start, LocalDate end) {
        return new Event(name, start, end);
    }

    /**
     * Marks the activity as completed.
     *
     * @return True if the activity was successfully marked, false if it was already marked.
     */
    public boolean mark() {
        if (isMarked) {
            return false;
        } else {
            isMarked = true;
            return true;
        }
    }

    /**
     * Unmarks the activity as completed.
     *
     * @return True if the activity was successfully unmarked, false if it was not marked.
     */
    public boolean unmark() {
        if (isMarked) {
            isMarked = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets a string representation of the activity's completion state.
     *
     * @return A string representing the completion state, "[X]" if marked, "[ ]" if unmarked.
     */
    public String getState() {
        return isMarked ? "[X]" : "[ ]";
    }

    /**
     * Checks if the activity name contains a specified input string.
     *
     * @param input The input string to check.
     * @return True if the name contains the input string, false otherwise.
     */
    public boolean checkName(String input) {
        return name.contains(input);
    }

    /**
     * Checks if the activity name exactly matches a specified input string.
     *
     * @param input The input string to check.
     * @return True if the name exactly matches the input string, false otherwise.
     */
    public boolean checkExactName(String input) {
        return name.equals(input);
    }

    /**
     * Gets the name of the activity.
     *
     * @return The name of the activity.
     */
    public String getName() {
        return name;
    }

    /**
     * Abstract method to convert the activity to a storage-friendly string representation.
     *
     * @return A string representing the activity in a storage-friendly format.
     */
    public abstract String toStorage();
}

