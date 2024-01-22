public class Todo extends Task {
    public Todo(String listItem) {
        super(listItem);
    }

    @Override
    public String toString() {
        return "[T]" +
                (this.taskDone ? "[X] " : "[ ] ") +
                this.listItem
        ;
    }
}
