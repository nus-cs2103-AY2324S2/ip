/**
 * Class for the delete command.
 */
public class Delete implements Command{
    private final int operand;
    private final Task deletedTask;
    private TaskList tasks;
    /**
     * Constructor
     * @param operand the index count from 0;
     */
    public Delete(int operand, TaskList taskList) throws WrongIndexException{
        this.operand=operand;
        if (operand>=taskList.getListLength() || operand<0) {
            throw new WrongIndexException(taskList.getListLength());
        }
        this.deletedTask=taskList.removeTask(operand);
        this.tasks=taskList;
    }

    /**
     * Print the deleted task and the number of tasks remaining
     */
    @Override
    public void reply() {
        System.out.println("    Noted. I've removed this task:");
        System.out.printf("      %s\n", this.deletedTask);
        System.out.printf("    Now you have %s tasks in the list.\n", this.tasks);
    }
}
