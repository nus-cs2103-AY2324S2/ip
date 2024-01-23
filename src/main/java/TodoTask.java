public class TodoTask extends Task {
    
    TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String status = super.isCompleted() ? "[X]" : "[ ]";
        return "[T]" + status + " " + super.getDescription();
    }
    
}
