package notduke.task;

/**
 * Represents a ToDo which is a type of Task
 */
public class ToDos extends Task {
    /**
     * Constructs a ToDo object with the specified name and mark whether it is done.
     * @param name The name of the ToDo
     * @param status The status of the ToDo
     */
    public ToDos(String name, Boolean status) {
        super(name, status);
    }

    /**
     * @InheritDoc Includes task type ToDos to string.
     */
    @Override
    public String saveOutput() {
        return "T " + super.saveOutput();
    }

    @Override
    public String printMatch(String match) {
        if (super.contains(match)) {
            return taskInfo();
        }
        return "";
    }

    /**
     * @InheritDoc Includes task type ToDos to string.
     */
    @Override
    public String taskInfo() {
        String output = "";
        output += "[T]";
        return output + super.taskInfo() + "\n";
    }
}
