package belle.command;

import belle.others.BelleException;
import belle.run.Storage;
import belle.run.TaskList;
import belle.run.Ui;
import belle.tasks.Task;

/**
 * Snooze item in list meaning
 * it allows deadlines to be edited.
 */
public class SnoozeCommand extends Command {
    private String msg;

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
     * @param storage Storage containing data of
     *          previous program.
     * @param taskList Tasklist of program.
     * @param ui Ui that handles user interactions.
     * @return Print statement for snooze command.
     * @throws BelleException If index specified
     *         does not exist in the list and
     *         command is not given in the right
     *         format.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws BelleException {
        try {
            int snoozeLength = 7; // as snooze + 1 space is 7 characters.
            String[] indexAndDeadlineList = this.msg.substring(snoozeLength).split(" to ");

            if (indexAndDeadlineList.length != 2) {
                throw new BelleException("Please enter command in the format ( snooze "
                        + "[index] to [new deadline] )");
            }

            String index = indexAndDeadlineList[0].trim();
            String deadline = indexAndDeadlineList[1];
            Task snoozeTask = taskList.getTask(Integer.valueOf(index) - 1);

            //high-level step to edit the deadline
            snoozeTask.snooze(snoozeTask, deadline);

            //high-level step that saves new list to harddisk
            storage.save(taskList.getList());

            String printStatement = "--------------------------" + "\n"
                    + "Nice! I have shifted the deadline of:" + "\n"
                    + snoozeTask.toString() + "\n" + "--------------------------";
            return printStatement;
        } catch (IndexOutOfBoundsException e) {
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

}
