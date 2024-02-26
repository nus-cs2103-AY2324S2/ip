package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;
import fireraya.task.Deadline;
import fireraya.task.DoAfter;
import fireraya.task.Task;

import java.util.Date;

/**
 * Class to handle a DoAfter Command in the program.
 *
 * This class is extended from the Command superclass.
 */
public class DoAfterCommand extends Command {

    private String task;
    private String after;
    private Date afterDate;

    /**
     * Constructor for a DoAfter Command.
     *
     * @param task String of the task to be done.
     * @param after String of the deadline of the task to be done.
     */
    public DoAfterCommand(String task, String after) {
        this.task = task;
        this.after = after;
    }

    /**
     * Overloaded constructor for a DoAfter Command.
     *
     * @param task String of the task to be done.
     * @param date A Java utils Date object of the deadline of the task to be done.
     */
    public DoAfterCommand(String task, Date date) {
        this.task = task;
        this.afterDate = date;
    }

    /**
     * Overrides the format to execute the command.
     *
     * @param tasks the Tasklist of program.
     * @param ui the Ui of the program.
     * @param storage the storage of the program.
     * @return String representing the message to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FirerayaException {
        DoAfter d;

        if (afterDate == null) {
            d = new DoAfter(task, after);
        } else{
            d = new DoAfter(task, afterDate);
        }

        tasks.add(d);
        String a = ui.taskAddedMsg(d, tasks.size());
        storage.saveToFile(tasks.getTasks());

        return a;
    }

}
