package lemona.command;

import lemona.oop.TaskList;

public class ListCommand extends Command{

    public ListCommand() {}

    @Override
    public String execute(TaskList tasks) {
        String str = tasks.list();
        return str;
    }
}
