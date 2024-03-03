package goblin.command;

import goblin.Storage;
import goblin.TaskList;
import goblin.Ui;
import goblin.exception.OrkException;
import goblin.task.Deadlines;
import goblin.task.Task;

public class AddDeadlineCommand extends Command {
    protected String description;

    /**
     * create a new AddDeadlineCommand object
     * @param description for the deadlines object
     */
    public AddDeadlineCommand(String description) {
        super();
        this.description = description;
    }

    /**
     * add the deadline to the list
     * @param list the list of tasks
     * @param ui handle ui
     * @param storage handle storage
     * @throws OrkException when the description is not complete
     */

    //Solution below inspired by https://github.com/nus-cs2103-AY1920S1/duke/pull/23/commits
    public void execute(TaskList list, Ui ui, Storage storage) throws OrkException {
        try {
            String[] detailsSplit = description.split("/by");
            if (detailsSplit.length == 0 || detailsSplit[0].trim().isEmpty()) {
                throw new OrkException("The deadline for what?! You dumb meat!");
            }
            if (detailsSplit.length < 2 || detailsSplit[1].trim().isEmpty()) {
                throw new OrkException("Come on, dumb meat. I need both the task and the date.");
            }
            String action = detailsSplit[0].trim();
            String deadline = detailsSplit[1].trim();
            Task Deadline = new Deadlines(action,deadline);
            TaskList.list.add(Deadline);
            int numberOfTasks = TaskList.list.size();
            ui.printAddedMessage(Deadline, numberOfTasks);
            storage.writeToDisk(list);
        } catch (OrkException exception) {
            ui.printException(exception);
        }
    }
}
