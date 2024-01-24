public class Todo extends Task{

    Todo (String name) {
        super(name);
    }

    @Override
    public String taskTypeDisplay() {
        return "[T]";
    }
}
