package gandalf;

public class ToDos extends Task {
    public ToDos(String nameOfTask) {
        super(nameOfTask);
    }
    @Override
    public String toString() {
        if (getStatus()) {
            return "[T][X] " + getNameOfTask();
        } else {
            return "[T][ ] " + getNameOfTask();
        }
    }
}
