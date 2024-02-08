package main.java.command;

import main.java.UiHandler;
import main.java.task.*;
import main.java.ChatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class EventCommand extends Command {
    private String description;
    private String startDateString;
    private String endDateString;
    public EventCommand(String description, String startDateString, String endDateString) {
        this.description = description;
        this.startDateString = startDateString;
        this.endDateString = endDateString;
    }
    public void execute(TaskList tasks, UiHandler ui) throws ChatException {
        try {
            LocalDateTime startDate = DateParser.parseDate(startDateString);
            LocalDateTime endDate = DateParser.parseDate(endDateString);
            Task eventTask = new EventTask(this.description, startDate, endDate);
            tasks.addTask(eventTask);
            ui.addTaskResponse(eventTask, tasks.getSize());
        } catch (ChatException e) {
            throw e;
        }
    };
    public boolean isExit() {
        return false;
    };
}