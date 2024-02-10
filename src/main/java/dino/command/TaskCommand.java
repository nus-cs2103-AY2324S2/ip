package dino.command;

import dino.Dino;
import dino.DinoException;
import dino.Parser;
import dino.Storage;
import dino.TaskList;

public class TaskCommand extends Command {

    private Dino.TaskType taskType;
    private String description;
    public TaskCommand(Dino.TaskType taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DinoException {
        StringBuilder printTask = new StringBuilder();

        if (description.isEmpty()) {
            throw new DinoException("Description cannot be empty.");
        }

        taskList.addTask(Parser.createTaskFromInput(taskType, description));

        printTask.append("Okay.\n");
        printTask.append("  ").append(taskList.get(taskList.size() - 1)).append("\n");
        printTask.append("Now you have ").append(taskList.size()).append(" in the list.\n");

        return printTask.toString();
    }
}
