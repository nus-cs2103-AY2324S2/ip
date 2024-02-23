package dylanbot;

import java.util.ArrayList;

/**
 * Represents a Task of type T == TodoTask
 */
public class TodoTask extends Task {

    /**
     * Creates a new TodoTask with the specified description
     *
     * @param desc The specified description
     */
    public TodoTask(String desc) {
        super("T", desc);
    }

    public TodoTask(String desc, ArrayList<String> tags) {
        super("T", desc, tags);
    }


    @Override
    public String toString() {
        return "[" + this.getType() + "] "
                + (this.checkCompleted() ? "[X]" : "[ ]")
                + " " + this.getDesc()
                + "\n\ttag(s): " + this.getTags();
    }
}
