package model;

public class ToDo extends Task{
    public ToDo(String label) {
        super(label);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
