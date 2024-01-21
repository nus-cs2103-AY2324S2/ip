/**
 * Class for task start with todo
 */
public class Todo extends Task{

    /**
     * constructor
     * @param descrip the dicription of the task
     */
    public Todo(String descrip) {
        super(descrip);
    }

    /**
     * Override the abstract class
     * @return T
     */
    @Override
    public String getTaskTypeIcon() {
        return "T";
    }

    /**
     * Override the abstract class
     * @return todo
     */
    @Override
    public String getTaskType() {
        return "todo";
    }
}
