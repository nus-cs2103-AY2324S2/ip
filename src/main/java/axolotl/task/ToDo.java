package axolotl.task;

/**
 * A ToDo object
 */
public class ToDo extends Task{
    static protected String ALIAS = "T"; // alias (i.e. T for ToDo)

    /**
     * Create a todo with following details:
     * @param taskName
     * @param status
     */
    public ToDo(String taskName, boolean status) {
        super(taskName, status);
    }

    /**
     * Return the string representation of this todo,
     * where status is either 'X' or ' '.
     * @return [T] [<status>] <task_name>
     */
    @Override
    public String toString() {
        return "[" + ALIAS + "]" + super.toString();
    }

    /**
     * Return the string representation of this todo to be stored in local file,
     * where the status is in string format (true/false).
     * @return T,<status (true/false)>,<task_name>
     */
    @Override
    public String toStore() {
        return ALIAS + super.toStore();
    }
}
