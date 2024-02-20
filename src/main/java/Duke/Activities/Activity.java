package Duke.Activities;

import java.time.LocalDate;

/**
 * The {@code Activity} interface defines the structure for activities that can be managed within the Duke application.
 * It provides methods for printing details about an activity, retrieving the name of the activity, and marking an activity
 * with a specific input, potentially to track completion or progress.
 */
public abstract class Activity {
    private final String name;
    boolean isMarked;

    public Activity(String name) {
        this.name = name;
        this.isMarked = false;
    }

    public static Activity of(String name) {
        return new Todo(name);
    }

    public static Activity of(String name, LocalDate date) {
        return new Deadline(name, date);
    }

    public static Activity of(String name, LocalDate start, LocalDate end) {
        return new Event(name, start, end);
    }

    public boolean mark() {
        if (isMarked) {
            return false;
        } else {
            isMarked = true;
            return true;
        }
    }

    public boolean unmark() {
        if (isMarked) {
            isMarked = false;
            return true;
        } else {
            return false;
        }
    }

    public String getState() {
        return isMarked ? "[√]" : "[X]";
    }

    public boolean checkName(String input) {
        return name.contains(input);
    }

    public boolean checkExactName(String input)  {
        return name.equals(input);
    }

    public String getName() {
        return name;
    }

    public abstract String toStorage();
}
