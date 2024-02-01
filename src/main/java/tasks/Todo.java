package tasks;

public class Todo extends Task{

    public Todo (String name) {
        super(name);
    }

    @Override
    public String taskTypeDisplay() {
        return "[T]";
    }
    @Override
    public String storeFormat() {
        String completeFormat = complete ? "1" : "0";
        return String.format("%s | %s | %s", "T", completeFormat, name);
    }
}
