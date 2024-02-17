package gandalf;

public class ToDos extends Task {
    public ToDos(String nameOfTask) {
        super(nameOfTask);
    }
    @Override
    public String toString() {
        if(status) {
            return "[T][X] " + nameOfTask;
        }
        else {
            return "[T][ ] " + nameOfTask;
        }
    }
}
