package kaiyap;

public class Todo extends Task {
    public Todo(String listItem, String inputItem) {
        super(listItem, inputItem);
    }

    @Override
    public String toString() {
        return "[T]" +
                (this.taskDone ? "[X] " : "[ ] ") +
                this.listItem
        ;
    }
}
