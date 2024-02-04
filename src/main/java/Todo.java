public class Todo extends Task {

    protected String by;
    String DIVIDER = " | ";


    public Todo(boolean isDone, String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getFileString() {
        return "T" + DIVIDER + (isDone ? "1" : "0") + DIVIDER + description;
    }

}
