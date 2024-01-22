public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String stringify() {
        return "[T]" + super.stringify();
    }
}
