public class Todo extends  Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if(this.isDone) {
            return "[T][X] "+ description;
        } else {
            return "[T][ ] " + description;
        }
    }
}
