package megatron.task;

/**
 * Task of ToDo type with only name
 */
public class ToDo extends Task {

    /** Type icon for ToDo task */
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
