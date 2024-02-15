package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;
import fireraya.task.Deadline;
import fireraya.task.Task;

import java.util.Date;

public class DeadlineCommand extends Command {

    private String task;
    private String deadline;
    private Date date;

    public DeadlineCommand(String task, String deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    public DeadlineCommand(String task, Date date) {
        this.task = task;
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FirerayaException {
        Deadline d;

        if (date == null) {
            d = new Deadline(task, deadline);
        } else{
            d = new Deadline(task, date);
        }
        tasks.add(d);
        String a = ui.taskAddedMsg(d, tasks.size());
        storage.saveToFile(tasks.getTasks());

        return a;
    }

}
