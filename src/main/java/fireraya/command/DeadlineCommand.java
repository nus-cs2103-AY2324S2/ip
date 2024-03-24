package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;
import fireraya.task.Deadline;
import fireraya.task.Task;

import java.util.Date;

/**
 * Class to handle a Deadline Command in the program.
 *
 * This class is extended from the Command superclass.
 */
public class DeadlineCommand extends Command {

    private String task;
    private String deadline;
    private Date date;

    /**
     * Constructor for a Deadline Command.
     *
     * @param task String of the task to be done.
     * @param deadline String of the deadline of the task to be done.
     */
    public DeadlineCommand(String task, String deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    /**
     * Overloaded constructor for a Deadline Command.
     *
     * @param task String of the task to be done.
     * @param date A Java utils Date object of the deadline of the task to be done.
     */
    public DeadlineCommand(String task, Date date) {
        this.task = task;
        this.date = date;
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
        Deadline d;

        if (date == null) {
            d = new Deadline(task, deadline);
        } else{
            d = new Deadline(task, date);
        }
        tasks.add(d);
        String a = ui.taskAddedMsg(d, tasks.size());
        storage.saveToFile(tasks.getTasks());

        return a;
    }

}
