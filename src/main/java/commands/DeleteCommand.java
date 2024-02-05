package commands;

import util.Ui;
import util.TaskList;
import util.Storage;
import exceptions.ChatBotException;
public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.deleteTask(this.index);
            storage.saveToFile(taskList);
        } catch (ChatBotException e) {
            System.out.println("\tAn error occurred when deleting");
        }
    }
}

