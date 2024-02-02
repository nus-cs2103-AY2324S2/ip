package bartenderbob;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String show() {
        super.status = isDone? "X": " ";
        return "[T]" + "[" + status + "]" + " " + this.description;
    }
}
