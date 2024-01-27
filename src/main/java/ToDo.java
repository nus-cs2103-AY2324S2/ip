public class ToDo extends Task {

    private static final String type = "T";
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveString() {
        return type + "," + super.toSaveString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

