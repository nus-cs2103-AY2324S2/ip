package thames.task;

/**
 * Class for tasks with no timeline.
 */
public class ToDo extends Task{
    public ToDo (String name) {
        super(name);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
