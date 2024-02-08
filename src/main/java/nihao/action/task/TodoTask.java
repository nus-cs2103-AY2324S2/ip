package nihao.action.task;

public class TodoTask extends Task{
    public TodoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TodoTask && ((TodoTask) obj).taskName.equals(taskName);
    }
}
