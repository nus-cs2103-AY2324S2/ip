package mike.task;

import mike.ListViewType;

public class Todo extends Task {
    private static final String TYPE = "Todo";
    public Todo(String description) {
        super(description, TYPE);
        tags.add(ListViewType.DATE);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
