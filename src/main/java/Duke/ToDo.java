package Duke;

import Duke.Task;

public class ToDo extends Task {

    protected String by;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}