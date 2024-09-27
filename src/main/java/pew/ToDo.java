package pew;

public class ToDo extends Task{
    protected String type = "T";

    /**
     * Constructor for ToDo
     *
     * @param index the index of the task
     * @param description the description of the task
     */
    public ToDo(int index, String description) {
        super(index, description);
    }

    @Override
    public String getTask() {
        return index + ". [" + type + "][" + getStatusIcon() + "] " + description;
    }

    @Override
    public String save() {
        return type + "|" + (isDone ? "1" : "0") + "|" + description;
    }
}
