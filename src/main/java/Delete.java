/**
 * Class for the delete command.
 */
public class Delete implements Command{
    private final int operand;
    private final Task deletedTask;

    /**
     * Constructor
     * @param operand the index count from 0;
     */
    public Delete(int operand) throws WrongIndexException{
        this.operand=operand;
        if (operand>=Task.task_list.size() || operand<0) {
            throw new WrongIndexException();
        }
        this.deletedTask=Task.task_list.remove(operand);
    }

    /**
     * Print the deleted task and the number of tasks remaining
     */
    @Override
    public void reply() {
        System.out.println("    Noted. I've removed this task:");
        System.out.printf("      %s\n", this.deletedTask);
        System.out.printf("    Now you have %s tasks in the list.\n", Task.task_list.size());
    }
}
