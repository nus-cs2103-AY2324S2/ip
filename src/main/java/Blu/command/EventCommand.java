package Blu.command;

import java.io.IOException;
import java.time.LocalDateTime;

import Blu.exception.StorageException;
import Blu.storage.Storage;
import Blu.task.Event;
import Blu.task.TaskList;
import Blu.ui.UI;

public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    public EventCommand(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.description = description;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws StorageException {
        Event event = new Event(description, fromDateTime, toDateTime);
        taskList.addTask(event);
        ui.showTaskAdded(event, taskList);
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            throw new StorageException("Failed to write to storage file");
        }
    }
    
}
