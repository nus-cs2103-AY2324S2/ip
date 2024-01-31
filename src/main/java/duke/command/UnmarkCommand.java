package duke.command;

import duke.tasks.Task;

import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;

import duke.others.BelleException;

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

    @Override
    public void execute(Storage s, TaskList t, Ui u) throws BelleException {
        try {
            Task doingtask = t.getTask(Integer.valueOf(index)-1);
            doingtask.setTaskUndone();
            System.out.println("--------------------------");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(doingtask.toString());
            System.out.println("--------------------------");
            s.save(t.getList());
        } catch (IndexOutOfBoundsException e){
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}