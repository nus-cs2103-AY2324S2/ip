package kervyn.Commands;
import kervyn.Storage;
import kervyn.Tasks.Task;
import kervyn.Tasks.TaskList;

public class ByeCommand extends Command {
    private Storage storage;
    public ByeCommand(TaskList taskList, Storage storage) {
        super("Bye", taskList);
        this.storage = storage;
    }

    @Override
    public void executeCommand() {
        System.out.println("\tBye. Hope to see you again soon!");
        for (Task userRequest : this.taskList.getTaskList()) {
            String content = userRequest.toString();
            storage.writeToFile(content);
        }
    }
}
