package main;

public class ToDo extends Task {
    public ToDo(String description){
        super(description);
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }

    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }
}
