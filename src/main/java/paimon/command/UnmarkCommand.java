package paimon.command;

import paimon.ChatException;
import paimon.UiHandler;
import paimon.task.Task;
import paimon.task.TaskList;

public class UnmarkCommand extends Command {
    private String indexString;
    public UnmarkCommand(String indexString) {
        this.indexString = indexString;
    }
    public void execute(TaskList taskList, UiHandler ui) throws ChatException {
        try {
            int markIndex = Integer.parseInt(indexString);
            if (markIndex >= 1 && markIndex <= taskList.getSize()) {
                Task markTask = taskList.getTask(markIndex - 1);
                if (taskList.markTask(markIndex - 1, false)) {
                    ui.markTaskResponse(markTask, true);
                } else {
                    System.out.println("Traveller, this task is already unmarked.");
                }
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