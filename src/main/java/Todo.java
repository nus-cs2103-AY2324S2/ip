public class Todo extends Task{
    
    public Todo(String taskString) {
        super(taskString);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Todo)) {
            return false;
        }

        Todo todo = (Todo) obj;
        return (super.equals(todo));
    }
    
}
