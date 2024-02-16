package rick.logic.command;

/**
 * A command which asks to list down all tasks in the task list.
 */
public class ListCommand implements Command {
    @Override
    public String[] respond() {
        return new String[]{"L"};
    }
}
