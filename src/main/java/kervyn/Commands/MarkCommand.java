package kervyn.Commands;

import kervyn.Tasks.TaskList;

public class MarkCommand extends Command {
    private String[] userInput;
    public MarkCommand(TaskList taskList, String[] userInput) {
        super("Mark", taskList);
        this.userInput = userInput;
    }

    @Override
    public void executeCommand() {
        taskList.markTask(taskList.getTaskList(), this.userInput);
    }
}
