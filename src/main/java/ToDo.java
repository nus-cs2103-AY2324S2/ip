public class ToDo extends Task {
    public ToDo(String c) {
        super(c);
    }

    @Override
    public String toString() {
        String s = "[T]" + super.toString();
        return s;
    }
}
