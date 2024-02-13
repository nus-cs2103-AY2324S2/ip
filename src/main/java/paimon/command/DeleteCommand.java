package paimon.command;

import paimon.ChatException;
import paimon.UiHandler;
import paimon.task.TaskList;

public class DeleteCommand extends Command {
    private String indexString;
    public DeleteCommand(String indexString) {
        this.indexString = indexString;
    }
    public void execute(TaskList taskList, UiHandler ui) throws ChatException {
        try {
            int deleteIndex = Integer.parseInt(indexString);
            if (deleteIndex >= 1 && deleteIndex <= taskList.getSize()) {
                taskList.deleteTask(deleteIndex - 1);
                ui.deleteTaskResponse(taskList.getSize());
            } else {
                System.out.println("Sorry Traveller, that task does not exist");
            }
        } catch (NumberFormatException e) {
            throw new ChatException("Sorry Traveller, your input is invalid");
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("Sorry Traveller, your index is out of bounds");
        }
    };
    public boolean isExit() {
        return false;
    };
}