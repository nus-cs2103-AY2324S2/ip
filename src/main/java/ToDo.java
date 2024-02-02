public class ToDo extends Task {
    ToDo(String name) {
        super(name);
    }

    String getCommand() {
        return "todo " + name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
