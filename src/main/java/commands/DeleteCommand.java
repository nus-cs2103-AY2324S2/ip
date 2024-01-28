package commands;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final String message;

    public DeleteCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            int index = Integer.parseInt(message);
            Task removed = tasks.get(index - 1);
            tasks.remove(index - 1);
            System.out.println("\t Uncle deleted this item:\n\t\t" + removed
                    + "\n\t Now you have " + tasks.numTasks() + " task(s) in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Uncle think that you input wrong index.\n\t Use 'list' to view all tasks");
        }
    }
}
