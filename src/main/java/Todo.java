public class Todo extends Task {
    private static final String TYPE_SYMBOL = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toCsv() {
        return TYPE_SYMBOL + "," + (super.getDone() ? "1" : "0") + "," + super.getDescription() + ",,";
    }

    @Override
    public String toString() {
        return "[" + TYPE_SYMBOL + "]" + super.toString();
    }
}
