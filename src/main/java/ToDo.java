public class ToDo extends Task {

    private static final String TYPE = "T";

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString();
    }
}
