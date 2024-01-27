public class ToDos extends Task{
    public ToDos(String description) {
        super(description.replaceFirst("todo ", ""));
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
