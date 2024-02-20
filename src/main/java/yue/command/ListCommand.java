package yue.command;

import yue.Storage;
import yue.tasks.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {


    /**
     * Executes the ListCommand, displaying the list of tasks.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage handler.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String list = "    " + (i + 1) + ". " + tasks.get(i);
            sb.append(list).append(System.lineSeparator());
        }

        String list = "    Here are the tasks in your list:\n"
                + sb.toString() + "\n";

        return list;
    }





    /**
     * Checks if the command is an exit command.
     *
     * @return Always returns false, as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

