package mike.task;

import mike.ListViewType;

/**
 * A Todo task.
 * @author ningc
 */
public class Todo extends Task {
    private static final String TYPE = "Todo";

    /**
     * Todo task contains a description of what to do.
     * @param description what it is the user needs to do.
     */
    public Todo(String description) {
        super(description, TYPE);
        tags.add(ListViewType.DATE);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
