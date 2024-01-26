
public class Todo extends Task {
    public Todo(String taskName) {
        this(taskName, false);
    }

    public Todo(String taskName, Boolean done) {
        super(taskName, done);
        super.identifier = "T";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
