package kervyn.Commands;

import kervyn.Tasks.TaskList;

public class UnMarkCommand extends Command {
    private String[] userInput;
    public UnMarkCommand(TaskList taskList, String[] userInput) {
        super("UnMark", taskList);
        this.userInput = userInput;
    }

    @Override
    public void executeCommand() {
        taskList.unMarkTask(taskList.getTaskList(), this.userInput);
    }
}
