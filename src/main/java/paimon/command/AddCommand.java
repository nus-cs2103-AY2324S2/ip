package paimon.command;

import paimon.exception.ChatBotParameterException;
import paimon.storage.Storage;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.ui.Ui;

public class AddCommand extends Command{

    public AddCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }
    
    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        Task task;
        String outputString = "";
        try {
            switch (this.keyword) {
            case "todo":
                task = taskList.addToDo(this.parameters);
                break;
            case "deadline":
                task = taskList.addDeadline(this.parameters);
                break;
            case "event":
                task = taskList.addEvent(this.parameters);
                break;
            default:
                return "";
            }
            outputString += ui.showAddedTask(task);
            outputString += ui.showTaskListStatus(taskList);
            return outputString;
        } catch (ChatBotParameterException e) {
            return ui.showError(e.getMessage());
        }
    }
}
