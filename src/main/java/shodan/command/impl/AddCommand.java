package shodan.command.impl;

import shodan.ShodanException;
import shodan.TaskList;
import shodan.command.Command;
import shodan.storage.StorageManager;
import shodan.tasks.Task;
import shodan.tasks.TaskParser;
import shodan.tasks.TaskType;
import shodan.ui.TermUi;

import java.util.List;

public class AddCommand extends Command {
    List<String> args;
    TaskType type;
    public AddCommand(List<String> args, String taskType) {
        this.args = args;
        this.type = TaskType.valueOf(taskType);
    }
    public boolean execute(TaskList tasks, StorageManager storageManager, TermUi ui) throws ShodanException {
        Task task = TaskParser.parse(args, type);
        int size = tasks.add(task);
        storageManager.saveTasks(tasks.getTasks());
        ui.printMsg("Task has been added:\n\t" + task);
        ui.printMsg(String.format("You have %d tasks now.\n", size));
        return false;
    }
}
