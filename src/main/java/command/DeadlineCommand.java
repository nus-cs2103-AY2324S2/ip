package main.java.command;

import main.java.UiHandler;
import main.java.task.DateParser;
import main.java.task.DeadlineTask;
import main.java.task.Task;
import main.java.task.TaskList;
import main.java.ChatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DeadlineCommand extends Command {
    private String description;
    private String endDateString;
    public DeadlineCommand(String description, String endDateString) {
        this.description = description;
        this.endDateString = endDateString;
    }
    public void execute(TaskList tasks, UiHandler ui) throws ChatException {
        try {
            LocalDateTime endDate = DateParser.parseDate(endDateString);
            Task deadlineTask = new DeadlineTask(this.description, endDate);
            tasks.addTask(deadlineTask);
            ui.addTaskResponse(deadlineTask, tasks.getSize());
        } catch (ChatException e) {
            throw e;
        }
    };
    public boolean isExit() {
        return false;
    };
}