package tasks;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, String status) {
        super(description, status);
    }

    @Override
    public String toSaveFormat() {
        return "T " + super.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
