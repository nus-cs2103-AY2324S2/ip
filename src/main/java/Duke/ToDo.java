package Duke;

public class ToDo extends Task {
    String label = "[T]";

    /**
     * Constructs ToDo object with specified String
     *
     * @param msg
     */
    public ToDo(String msg) {
        super(msg);
    }

    @Override
    public boolean isDuplicate(Task task) {
        if (task instanceof ToDo) {
            return this.compareMsg(task);
        }
        return false;
    }

    @Override
    public String toString() {
        return label + super.toString();
    }

}
