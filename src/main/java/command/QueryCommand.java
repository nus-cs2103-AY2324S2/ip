package command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import tasklist.TaskList;
import ui.UI;

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
     * @return A string containing the result message for this operation.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        switch (this.commandType) {
        case QUERY:
            return query(tasks);
        case FIND:
            return find(tasks);
        default:
            return "";
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
     * @return A string containing the result message for this operation.
     */
    public String query(TaskList tasks) {
        String formattedDate = this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        if (this.query.equals("deadline")) {
            UI.print("Below are deadlines that are due on " + formattedDate);
            String result = "Below are deadlines that are due on " + formattedDate + "\n";
            for (Task task : tasks.fetchAll()) {
                if (task instanceof Deadline && task.queryByDate(this.date)) {
                    UI.print(task);
                    result += task + "\n";
                }
            }
            return result;
        } else if (this.query.equals("event")) {
            UI.print("Below are events that are operating on " + formattedDate);
            String result = "Below are events that are operating on " + formattedDate + "\n";
            for (Task task : tasks.fetchAll()) {
                if (task instanceof Event && task.queryByDate(date)) {
                    UI.print(task);
                    result += task + "\n";
                }
            }
            return result;
        } else {
            UI.print("Could not query given task type");
            return "Could not query given task type";
        }
    }

    /**
     * Finds and prints tasks in the task list that match the specified query.
     *
     * @param tasks   The TaskList from which tasks are queried.
     * @return A string containing the result message for this operation.
     */
    public String find(TaskList tasks) {
        UI.print("Here are the matching tasks in your list:");
        String result = "Here are the matching tasks in your list:\n";
        int index = 1;
        List<Task> queriedTasks = tasks.fetchAll();
        queriedTasks = queriedTasks.stream().filter(
                task -> task.getName().toLowerCase().contains(this.query)).collect(Collectors.toList());
        for (Task task : queriedTasks) {
            UI.print(index + "." + task);
            result += index + "." + task + "\n";
            index++;
        }
        return result;
    }
}
