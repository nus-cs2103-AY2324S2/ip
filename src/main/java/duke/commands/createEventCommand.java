package commands;

import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static constants.Constant.DATE_TIME_FORMATTER_FOR_PRINT;

public class createEventCommand extends Command{
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    public createEventCommand(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        Task newTask = null;
        newTask = new Event(description, start, end, DATE_TIME_FORMATTER_FOR_PRINT);
        tasks.addTask(newTask);
        ui.showLine();
        newTask.displayTask(tasks.size());
        ui.showLine();
        return true;
    }


}
