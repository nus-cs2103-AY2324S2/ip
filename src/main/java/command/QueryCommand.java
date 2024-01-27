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

/**
 * The QueryCommand class represents a command to query tasks based on type and date.
 * It extends the Command class and implements the execute method to perform the specified query action.
 */
public class QueryCommand extends Command {

    /**
     * The type of query command.
     */
    private Command.Types commandType;

    /**
     * The type of task to be queried (deadline, event).
     */
    private String query;

    /**
     * The date used for querying tasks.
     */
    private LocalDate date;

    /**
     * Constructs a QueryCommand with the specified command type, query type, and date.
     *
     * @param commandType The type of query command.
     * @param query   The type of task to be queried (deadline, event).
     * @param date        The date used for querying tasks.
     */
    public QueryCommand(Command.Types commandType, String query, LocalDate date) {
        this.commandType = commandType;
        this.query = query;
        this.date = date;
    }

    /**
     * Executes the QueryCommand, performing the specified query action on the given task list and storage.
     *
     * @param tasks   The TaskList on which the query action is performed.
     * @param storage The Storage where changes are saved.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        switch (this.commandType) {
            case QUERY:
                query(tasks);
                break;
            case FIND:
                find(tasks);
                break;
        }
    }

    /**
     * Retrieves the query type as test data associated with QueryCommand.
     *
     * @return The query type used for testing.
     */
    @Override
    public String getTestData() {
        return this.query;
    }

    /**
     * Finds tasks based on the specified query type and date, printing the results to the UI.
     *
     * @param tasks   The TaskList from which tasks are queried.
     */
    public void query(TaskList tasks) {
        String formattedDate = this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        if (this.query.equals("deadline")) {
            UI.print("Below are deadlines that are due on " + formattedDate);
            for (Task task : tasks.fetchAll()) {
                if (task instanceof Deadline && task.queryByDate(this.date)) {
                    UI.print(task);
                }
            }
        } else if (this.query.equals("event")) {
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

    /**
     * Finds and prints tasks in the task list that match the specified query.
     *
     * @param tasks   The TaskList from which tasks are queried.
     */
    public void find(TaskList tasks) {
        UI.print("Here are the matching tasks in your list:");
        int index = 1;
        for (Task task : tasks.fetchAll()) {
            if (task.getName().contains(this.query)) {
                UI.print(index + "." + task);
            }
        }
    }
}