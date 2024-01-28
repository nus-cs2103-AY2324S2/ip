package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private final String message;

    public MarkCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            int index = Integer.parseInt(message);
            tasks.get(index - 1).mark();
            System.out.println("\t Nice! Uncle marked this task as done:\n\t\t" + tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Uncle think that you input wrong index.\n\t Use 'list' to view all tasks");
        }
    }
}
