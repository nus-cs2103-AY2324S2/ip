package paimon.command;

import paimon.ChatException;
import paimon.UiHandler;
import paimon.DateParser;
import paimon.task.EventTask;
import paimon.task.Task;
import paimon.task.TaskList;

import java.time.LocalDateTime;

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
            ui.addTaskResponse(eventTask.getTask(), tasks.getSize());
        } catch (ChatException e) {
            throw e;
        }
    };
    public boolean isExit() {
        return false;
    };
}