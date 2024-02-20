package Duke.Activities;

import java.util.List;

/**
 * The {@code Todo} class represents a to-do task, implementing the {@link Activity} interface. It encapsulates
 * a simple task with a status indicating whether the task is completed or not, and a name describing the task.
 */
public class Todo extends Activity {
    public Todo(String input) {
        super(input);
    }

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

    @Override
    public String toString() {
        return "[T] " + super.getState() + " " + super.getName();
    }
}
