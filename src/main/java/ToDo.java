public class ToDo extends Task{
    static protected String ALIAS = "T";
    public ToDo(String taskName, boolean status) {
        super(taskName, status);
    }

    @Override
    public String toString() {
        return "[" + ALIAS + "]" + super.toString();
    }

    @Override
    public String toStore() {
        return ALIAS + super.toStore();
    }
}
