public class ToDo extends Task {
    public ToDo(String input) {
        super(input);
    }

    @Override
    public String toString() {
        if (super.done){
            return "[T][X] " + super.taskContent;
        }
        return "[T][ ] " + super.taskContent;
    }
}
