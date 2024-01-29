package kervyn.Commands;

import kervyn.Tasks.TaskList;

public class DeleteCommand extends Command {
    private String[] userInput;
    public DeleteCommand(TaskList taskList, String[] userInput) {
        super("Delete", taskList);
        this.userInput = userInput;
    }

    @Override
    public void executeCommand() {
        taskList.removeTask(taskList.getTaskList(), this.userInput);
    }
}
