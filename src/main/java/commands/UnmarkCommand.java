package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private final String message;

    public UnmarkCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            int index = Integer.parseInt(message);
            tasks.get(index - 1).unmark();
            System.out.println("\t Ok, Uncle marked this task as not done yet:\n\t\t"
                    + tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Uncle think that you input wrong index.\n\t Use 'list' to view all tasks");
        }
    }
}
