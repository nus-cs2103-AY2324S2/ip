package duke.command;

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
    private String index;
    private String date;

    /**
     * Constructs SnoozeCommand.
     *
     * @param index The index of item to delete.
     */
    public SnoozeCommand(String index, String date) {
        this.index = index;
        this.date = date;
    }

    @Override
    public String execute(Storage s, TaskList t, Ui u) throws BelleException {
        try {
            Task snoozeTask = t.getTask(Integer.valueOf(index) - 1);
            if (snoozeTask.getType().equals("T")) {
                return "Todo tasks cannot be snoozed as they do not have a deadline";
            } else if (snoozeTask.getType().equals("D")) {
                DeadlineTask currTask = (DeadlineTask) snoozeTask;
                currTask.setDeadline(this.date);
            } else {
                EventTask currTask = (EventTask) snoozeTask;
                currTask.setDeadline(this.date);
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
