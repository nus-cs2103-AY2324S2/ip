package tiny.tasks;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }    

    @Override
    public String toSave() {
        return "T" + super.toSave();
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
