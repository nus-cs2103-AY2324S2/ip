package xilef.command;

import xilef.XilefException;
import xilef.Storage;
import xilef.Ui;
import xilef.task.Task;
import xilef.task.TaskList;

public class FindCommand extends Command{

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws XilefException {
        TaskList list = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.description.contains(this.keyword)) {
                list.add(task);
            }

        }
        if (list.size() == 0) {
            return "\t\tThere are no matching tasks in your list";
        } else {
            StringBuilder s = new StringBuilder("\t\tThese are the matching tasks in your list");
            for (int i = 1; i < list.size() + 1; i++) {
                Task t = list.get(i - 1);
                s.append("\n\t\t").append(i).append(".").append(t.toString());
            }
            return s.toString();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
