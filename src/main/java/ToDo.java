public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }
    public ToDo(String task, boolean done) {
        super(task, done);
    }

    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }
}
