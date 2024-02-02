package Tasks;

import Tasks.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }

    @Override
    public String getCommand() {
        return String.format("todo %s\n%b\n", this.description, this.isDone);
    }
}
