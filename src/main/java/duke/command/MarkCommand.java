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

    /**
     * Runs the command to mark a task as done.
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

            Task doingTask = t.getTask(Integer.valueOf(index) - 1);
            doingTask.setTaskDone();
            s.save(t.getList());
            return generateMarkStatement(doingTask);
        } catch (IndexOutOfBoundsException e) {
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

    /**
     * Generates mark print statement.
     *
     * @param doingTask Task to be marked.
     * @return Print statement for marked task.
     */
    public String generateMarkStatement(Task doingTask) {
        return "--------------------------" + "\n"
                + "Nice! I have marked this task as done:" + "\n"
                + doingTask.toString() + "\n" + "--------------------------";
    }

}
