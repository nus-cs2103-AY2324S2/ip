package main.java.command;

import main.java.UiHandler;
import main.java.task.*;
import main.java.ChatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class MarkCommand extends Command {
    private String indexString;
    public MarkCommand(String indexString) {
        this.indexString = indexString;
    }
    public void execute(TaskList taskList, UiHandler ui) throws ChatException {
        try {
            int markIndex = Integer.parseInt(indexString);
            if (markIndex >= 1 && markIndex <= taskList.getSize()) {
                Task markTask = taskList.getTask(markIndex - 1);
                if (taskList.markTask(markIndex - 1, true)) {
                    ui.markTaskResponse(markTask, true);
                } else {
                    System.out.println("Traveller, this task is already marked as done!");
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