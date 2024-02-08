package main.java.command;

import main.java.UiHandler;
import main.java.task.*;
import main.java.ChatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;

    }
    public void execute(TaskList taskList, UiHandler ui) throws ChatException {
        Task eventTask = new TodoTask(this.description);
        taskList.addTask(eventTask);
        ui.addTaskResponse(eventTask, taskList.getSize());
    };
    public boolean isExit() {
        return false;
    };
}