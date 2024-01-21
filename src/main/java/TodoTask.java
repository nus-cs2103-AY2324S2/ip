public class TodoTask extends Task{
    public TodoTask(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        return " [T]" + super.toString();
    }
}
