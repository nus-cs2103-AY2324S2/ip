package duke.command;

import duke.Belle;
import duke.others.BelleException;
import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;

/**
 * Snooze item in list.
 * Command is in format "snooze [index] to [new deadline]"
 */
public class SnoozeCommand extends Command {
    private String msg;
    enum Type {
        E,
        D
    }

    /**
     * Constructs SnoozeCommand.
     *
     * @param input Users input.
     */
    public SnoozeCommand(String input) {
        this.msg = input;
    }

    /**
     * Runs the command to snooze a task and edit
     * the deadline.
     *
     * @param s Storage containing data of
     *          previous program.
     * @param t Tasklist of program.
     * @param u Ui that handles user interactions.
     */
    @Override
    public String execute(Storage s, TaskList t, Ui u) throws BelleException {
        try {
            int snoozeLength = 7; // as snooze + 1 space is 7 characters.
            String[] indexAndDeadline = this.msg.substring(snoozeLength).split(" to ");
            if (indexAndDeadline.length != 2) {
                throw new BelleException("Please enter command in the format ( snooze "
                        + "[index] to [new deadline] )");
            }
            String index = indexAndDeadline[0].trim();
            String deadline = indexAndDeadline[1];
            Task snoozeTask = t.getTask(Integer.valueOf(index) - 1);
            if (snoozeTask.getType().equals(Type.D.name())) {
                DeadlineTask currTask = (DeadlineTask) snoozeTask;
                currTask.setDeadline(deadline);
            } else if (snoozeTask.getType().equals(Type.E.name())) {
                EventTask currTask = (EventTask) snoozeTask;
                currTask.setDeadline(deadline);
            } else {
                throw new BelleException("Trying to snooze an invalid Task type");
            }
            String printStatement = "--------------------------" + "\n"
                    + "Nice! I have shifted the deadline of:" + "\n"
                    + snoozeTask.toString() + "\n" + "--------------------------";

            //high-level step that saves new list to harddisk
            s.save(t.getList());
            return printStatement;
        } catch (IndexOutOfBoundsException e) {
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

}
