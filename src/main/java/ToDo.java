public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String describe() {
        return String.format("[T]%s", super.describe());
    }
}
