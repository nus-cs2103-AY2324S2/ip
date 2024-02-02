package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.Task;
import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

/**
 * Adds the specified task to the list of tasks.
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * Constructor for the AddCommand class.
     * @param newTask The task to be added.
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList listStore, Ui ui, Storage storage) {
        listStore.add(newTask);
        int listCount = listStore.getTaskListSize();
        System.out.println("Got it. I've added this task:\n" + newTask.toString());
        if (listCount == 1) {
        System.out.println("Now you have " + listCount + " task in the list.\n");
        } else {
            System.out.println("Now you have " + listCount + " tasks in the list.\n");
        }
    }
}
