package duke.command;

import duke.others.BelleException;
import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;
import duke.tasks.Task;

/**
 * Marks items as undone.
 */
public class UnmarkCommand extends Command {
    private String index;

    /**
     * Constructs UnmarkCommand.
     *
     * @param index The index of item to mark as undone.
     */
    public UnmarkCommand(String index) {
        this.index = index;
    }

    /**
     * Runs the command to mark a task as not done.
     *
     * @param s Storage containing data of
     *          previous program.
     * @param t Tasklist of program.
     * @param u Ui that handles user interactions.
     */
    @Override
    public String execute(Storage s, TaskList t, Ui u) throws BelleException {
        try {
            assert (Integer.valueOf(this.index) >= 0) : "this index is negative";
            assert (Integer.valueOf(this.index) <= t.getSize()) : "this index is too big";
            Task doingtask = t.getTask(Integer.valueOf(index) - 1);
            doingtask.setTaskUndone();
            String printStatement = "--------------------------" + "\n"
                    + "OK, I've marked this task as not done yet:" + "\n"
                    + doingtask.toString() + "\n" + "--------------------------";

            //high-level step that saves new list to harddisk
            s.save(t.getList());
            return printStatement;
        } catch (IndexOutOfBoundsException e) {
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }
}
