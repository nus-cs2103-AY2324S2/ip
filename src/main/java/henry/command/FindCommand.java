package henry.command;

import java.util.ArrayList;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;
import henry.Ui;
import henry.task.Task;

/**
 * Represents a command to find contacts.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand object.
     * @param args The arguments of the command.
     * @throws HenryException If the command is invalid.
     */
    public FindCommand(String args) throws HenryException {
        if (args.isBlank()) {
            throw new HenryException("No keyword provided");
        }
        this.keyword = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        ArrayList<Task> list = tasks.findTasksByKeyword(this.keyword);
        System.out.println("Here is a list of tasks:");
        for (int i = 0; i < list.size(); i = i + 1) {
            System.out.printf("%d. %s\n", i + 1, list.get(i));
        }
        System.out.println();
    }

}
