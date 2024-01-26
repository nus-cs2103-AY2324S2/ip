/**
 * class for mark an task to undone.
 */
public class Unmark implements Command{
    private final int oprand;
    private TaskList tasks;
    /**
     * Constructor
     * change the status of task to unDone.
     * @param operand which task to mark from 0
     * @throws WrongIndexException index invalid
     */
    public Unmark(int operand, TaskList taskList) throws WrongIndexException{
        if (operand>=taskList.getListLength() || operand<0) {
            throw new WrongIndexException(taskList.getListLength());
        }
        this.oprand=operand;
        taskList.getTask(oprand).unDone();
    }

    /**
     * Override the reply method in interface
     */
    @Override
    public void reply() {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.printf("    %s\n",tasks.getTask(oprand));
    }
}
