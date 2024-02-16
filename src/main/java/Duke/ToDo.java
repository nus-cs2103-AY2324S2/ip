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
    public String toString() {
        return label + super.toString() + " ";
    }

}
