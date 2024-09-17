package chatbot;

import java.io.Serializable;

/**
 * Represents the simplest type of task, with only a description.
 */
public class TodoTask extends Task implements Serializable {
    private static final long serialVersionUID = 1L;

    public TodoTask(String desc) {
        super(desc);
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
