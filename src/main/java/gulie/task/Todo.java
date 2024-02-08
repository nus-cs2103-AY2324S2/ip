package gulie.task;

import java.time.format.DateTimeFormatter;

public class Todo extends Task {
    public Todo(String name) {
        this(name, false);
    }

    public Todo(String name, boolean mark) {
        super(name, mark);
    }

    @Override
    public String toSaveString() {
        return String.format("T\t%s", super.toSaveString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toString(DateTimeFormatter dtf) {
        return toString();
    }
}
