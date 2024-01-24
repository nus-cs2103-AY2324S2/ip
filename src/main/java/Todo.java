//// Solution below adapted by week2 iP Level-3 Partial solution
public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
