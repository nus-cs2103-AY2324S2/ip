package duke.command;

import duke.others.BelleException;
import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;
import duke.tasks.Task;


/**
 * Marks items as done.
 */
public class MarkCommand extends Command {
    private String index;

    /**
     * Constructs MarkCommand.
     *
     * @param index The index of item to mark as done.
     */
    public MarkCommand(String index) {
        this.index = index;
    }

    @Override
    public String execute(Storage s, TaskList t, Ui u) throws BelleException {
        try {
            assert (Integer.valueOf(this.index) >= 0) : "this index is negative";
            assert (Integer.valueOf(this.index) <= t.getSize()) : "this index is too big";
            Task doingtask = t.getTask(Integer.valueOf(index) - 1);
            doingtask.setTaskDone();
            String printStatement = "--------------------------" + "\n"
                    + "Nice! I have marked this task as done:" + "\n"
                    + doingtask.toString() + "\n" + "--------------------------";

            //high-level step that saves new list to harddisk
            s.save(t.getList());
            return printStatement;
        } catch (IndexOutOfBoundsException e) {
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

}
