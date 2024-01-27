package command;

import task.Deadline;
import task.Event;
import task.Task;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class QueryCommand extends Command{
    private Command.Types commandType;
    private String queryType;
    private LocalDate date;

    public QueryCommand(Command.Types commandType, String queryType, LocalDate date) {
        this.commandType = commandType;
        this.queryType = queryType;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        switch (this.commandType) {
            case FIND:
                find(tasks, storage);
        }
    }

    public void find(TaskList tasks, Storage storage) {
        String formattedDate = this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        if (this.queryType.equals("deadline")) {
            UI.print("Below are deadlines that are due on " + formattedDate);
            for (Task task : tasks.fetchAll()) {
                if (task instanceof Deadline && task.queryByDate(this.date)) {
                    UI.print(task);
                }
            }
        } else if (this.queryType.equals("event")) {
            UI.print("Below are events that are operating on " + formattedDate);
            for (Task task : tasks.fetchAll()) {
                if (task instanceof Event && task.queryByDate(date)) {
                    UI.print(task);
                }
            }
        } else {
            UI.print("Could not query given task type");
        }
    }
}
