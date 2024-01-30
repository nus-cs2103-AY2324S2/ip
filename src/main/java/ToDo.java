public class ToDo extends Task {

    private static final String TYPE_SYMBOL = "[T]";

    public ToDo(String description) {
        super(description, TYPE_SYMBOL);
    }

    public ToDo(String description, Boolean isDone) {
        super(description, TYPE_SYMBOL, isDone);
    }
}
