package paimon.command;

import paimon.ChatException;
import paimon.UiHandler;
import paimon.DateParser;
import paimon.task.DeadlineTask;
import paimon.task.Task;
import paimon.task.TaskList;
import java.time.LocalDateTime;


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
            ui.addTaskResponse(deadlineTask.getTask(), tasks.getSize());
        } catch (ChatException e) {
            throw e;
        }
    };
    public boolean isExit() {
        return false;
    };
}