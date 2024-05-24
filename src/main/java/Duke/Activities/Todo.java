package Duke.Activities;

/**
 * Represents a Todo activity, which is a type of activity without a specific date or duration.
 */
public class Todo extends Activity {

    /**
     * Constructor to initialize a Todo activity with a specified name.
     *
     * @param name The name of the Todo activity.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Converts the Todo activity to a storage-friendly string format.
     *
     * @return A string representing the Todo activity in a storage-friendly format.
     */
    @Override
    public String toStorage() {
        String str = "todo ";
        str += super.getName();
        if (super.isMarked) {
            str += " /isMarked 1";
        } else {
            str += " /isMarked 0";
        }
        return str;
    }

    /**
     * Returns a formatted string representation of the Todo activity.
     *
     * @return A string containing details of the Todo activity, including name and completion state.
     */
    @Override
    public String toString() {
        return "[T] " + super.getState() + " " + super.getName();
    }
}
