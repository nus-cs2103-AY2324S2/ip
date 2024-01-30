public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }

    @Override
    public String saveTask() {
        return "T | " + super.saveTask();
    }
}
