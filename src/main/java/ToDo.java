public class ToDo extends Task {
    protected String description;
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return description;
    }
}
