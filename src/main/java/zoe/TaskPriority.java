package zoe;

public enum TaskPriority {
    TODO(0),
    EVENT(1),
    DEADLINE(2);

    private int priority;
    private TaskPriority(int priority){
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
