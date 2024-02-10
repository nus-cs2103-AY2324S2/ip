package commands;

import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static constants.Constant.DATE_TIME_FORMATTER_FOR_PRINT;

public class createDeadlineCommand extends Command{
    private String description;
    private LocalDateTime deadline;



    public createDeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        Task newTask = new Deadline(description, deadline, DATE_TIME_FORMATTER_FOR_PRINT);
        tasks.addTask(newTask);
        ui.showLine();
        newTask.displayTask(tasks.size());
        ui.showLine();
        return true;
    }


}
