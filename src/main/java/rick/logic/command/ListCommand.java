package rick.logic.command;

/**
 * A command which asks to list down all tasks in the task list.
 */
public class ListCommand implements Command {
    /**
     * Returns a string array containing important arguments of a bye command.
     * @return a string containing command type and related information.
     */
    @Override
    public String[] respond() {
        return new String[]{"L"};
    }
}
