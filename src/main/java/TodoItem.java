public class TodoItem {
    private final String description;
    private boolean isDone;

    public TodoItem(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isItemDone() {
        return this.isDone;
    }

    public void markItem() {
        this.isDone = true;
    }

    public void unmarkItem() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "]" + " " + this.description;
    }
}
