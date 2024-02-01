public class ToDo extends Task {
    public ToDo(String s) {
        super(s);
    }
    public ToDo(String status, String description) {
        super(description);
        super.setStatus(status);
    }
    @Override
    public String writeObject() {
        return String.format("todo %s \n", super.writeObject());
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
