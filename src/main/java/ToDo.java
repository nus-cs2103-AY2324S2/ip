public class ToDo extends Task {
    String label = "[T]";

    public ToDo(String msg) {
        super(msg);
    }
    @Override
    public String toString() {
        return label + super.toString() + " ";
    }

}
