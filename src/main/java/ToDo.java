public class ToDo extends Task {
    public ToDo(String taskName, boolean isDone) { super(taskName, isDone); }

    @Override
    public String storeData() {
        return "todo|" + super.storeData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
