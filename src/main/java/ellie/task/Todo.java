package ellie.task;

// To do class:  tasks without any date/time attached to it
//  e.g., visit new theme park
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int isDone) {
        super(description, isDone);
    }

    @Override
    public char getTaskType() {
        return 'T';
    }

    @Override
    public String listTaskString() {
        return "[T]" + super.listTaskString();
    }

}
