package lemona.command;

import lemona.oop.TaskList;

/**
 * A ListCommand class to handle list command.
 */
public class ListCommand extends Command{

    /**
     * Constructs ListCommand object to handle list command.
     */
    public ListCommand() {}

    @Override
    public String execute(TaskList tasks) {
        String str = tasks.list();
        return str;
    }
}
