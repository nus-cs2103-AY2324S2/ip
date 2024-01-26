public class Todo extends Task {
    protected String type;

    public Todo(String description) {
        super(description);
        this.type = "[T]";
    }

    @Override
    public String getType() {
        return type;
    }
}
