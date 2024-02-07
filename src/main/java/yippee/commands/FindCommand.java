package yippee.commands;

import java.util.ArrayList;
import java.util.stream.Collectors;

import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;
import yippee.tasks.Task;

/**
 * Represents command that displays tasks with a specific keyword
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Instantiates new FindCommand instance
     * @param keyword String to search for in names of tasks
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     * @param tasks TaskList of active tasks
     * @param ui Ui instance to print responses.
     * @param storage Storage instance to store any data.
     * @throws InvalidCommandException If command is of invalid format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        ArrayList<Task> taskList = tasks.getList();
        ArrayList<Task> filteredList = taskList.stream()
                .filter(task -> task.getName().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        ui.printList(new TaskList(filteredList));
    }
}
