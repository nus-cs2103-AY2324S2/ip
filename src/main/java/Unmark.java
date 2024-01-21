/**
 * class for mark an task to undone.
 */
public class Unmark implements Command{
    private final int oprand;

    /**
     * Constructor
     * change the status of task to unDone.
     * @param operand which task to mark from 0
     * @throws WrongIndexException index invalid
     */
    public Unmark(int operand) throws WrongIndexException{
        if (operand>=Task.task_list.size() || operand<0) {
            throw new WrongIndexException();
        }
        this.oprand=operand;
        Task.task_list.get(oprand).unDone();
    }

    /**
     * Override the reply method in interface
     */
    @Override
    public void reply() {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.printf("    %s\n",Task.task_list.get(oprand));
    }
}
