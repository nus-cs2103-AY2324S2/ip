package cappy.task;

public class Todo extends Task {
    public static final String TYPE_SYMBOL = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toCsv() {
        return TYPE_SYMBOL + "," + (super.getDone() ? "1" : "0") + "," + super.getDescription() + ",,";
    }

    @Override
    public String toString() {
        return "[" + TYPE_SYMBOL + "]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo)) {
            return false;
        }
        Todo other = (Todo) obj;
        return super.equals(other);
    }
}
