package duke.command;

import duke.others.BelleException;
import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;
import duke.tasks.Task;

/**
 * Deletes item from list.
 */
public class DeleteCommand extends Command {
    private String index;

    /**
     * Constructs DeleteCommand.
     *
     * @param index The index of item to delete.
     */
    public DeleteCommand(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    /**
     * Runs the command to delete tasks from tasklist.
     *
     * @param s Storage containing data of
     *          previous program.
     * @param t Tasklist of program.
     * @param u Ui that handles user interactions.
     */
    @Override
    public String execute(Storage s, TaskList t, Ui u) throws BelleException {
        try {
            assert (Integer.valueOf(index) > 0) : "index cannot be negative";
            String printStatement;
            assert (Integer.valueOf(this.index) >= 0) : "this index is negative";
            assert (Integer.valueOf(this.index) <= t.getSize()) : "this index is too big";
            Task deletetask = t.getTask(Integer.valueOf(index) - 1);
            t.removeTask(Integer.parseInt(index) - 1);
            printStatement = "--------------------------" + "\n"
                    + "Noted. I've removed this task:" + "\n"
                    + deletetask.toString() + "\n" + "Now you have "
                    + t.getSize() + " tasks in the list." + "\n"
                    + "--------------------------";

            //high-level step that saves new list to harddisk
            s.save(t.getList());
            return printStatement;
        } catch (IndexOutOfBoundsException e) {
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

}
