package sam.command;

import java.util.ArrayList;

import sam.SamException;
import sam.Storage;
import sam.TaskList;
import sam.Ui;
import sam.task.Task;

/**
 * Represents a command to find tasks with a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword for searching.
     *
     * @param keyword the keyword for searching
     * @throws SamException if the provided keyword is blank
     */
    public FindCommand(String keyword) throws SamException {
        if (keyword.isBlank()) {
            throw new SamException("Please provide a keyword for searching.");
        }
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SamException {
        ArrayList<Task> list = tasks.findTasksByKeyword(this.keyword);
        System.out.println("These are the tasks with the specified keyword");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(((i + 1) + "." + list.get(i)));
        }
    }
}
