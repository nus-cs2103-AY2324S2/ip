import enums.TaskStatus;

class ToDos extends Task{
    public ToDos(String description, TaskStatus isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}