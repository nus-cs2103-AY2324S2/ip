public class Todo extends Task {

    Todo(String content) {
        super(content);
    }

    Todo(boolean isComplete, String content) {
        super(isComplete, content);
    }
    @Override
    public Todo markDone() {
        return new Todo(true, this.content);
    }
    @Override
    public Todo unmarkTask() {
        return new Todo(false, this.content);
    }




    public String toString() {
        if (this.isComplete) {
            return String.format("[T][X] %s", this.content);
        } else {
            return String.format("[T][ ] %s", this.content);
        }
    }
}
