public class Todo extends Task {
    public Todo(String d, int s) {
        super(d, s);
    }

    @Override
    public String toString() {
        if (this.getStatus() == 0) {
            return "[T] [ ] " + this.getDesc();
        } else {
            return "[T] [X] " + this.getDesc();
        }
    }
}
