package simpli.interpreter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import simpli.actions.Action;
import simpli.exceptions.ActionException;
import simpli.exceptions.TaskException;
import simpli.tasks.Task;
import simpli.tasks.TaskList;

/**
 * Interpret and executes the tokens.
 */
public class Interpreter {
    private TaskList taskList;

    /**
     * Initializes the interpreter with the specified ui object and taskList.
     *
     * @param taskList Manages tasks and their actions.
     */
    public Interpreter(TaskList taskList) {
        assert taskList != null : "TaskList object not found";
        this.taskList = taskList;
    }

    /**
     * Interprets and executes the specified actions.
     *
     * @param tokens Array of token.
     * @return the String to be shown to the user.
     * @throws ActionException When no such action exists.
     * @throws TaskException When task have invalid parameters.
     */
    public String interpret(String[] tokens) throws ActionException, TaskException {
        LocalDateTime[] dates = interpretLocalDateTime(tokens);

        Action actionType = Action.valueOf(tokens[0].toUpperCase());
        switch (actionType) {
        case LIST: {
            return interpretList();
        }
        case MARK: {
            return interpretMark(tokens);
        }
        case UNMARK: {
            return interpretUnmark(tokens);
        }
        case DELETE: {
            return interpretDelete(tokens);
        }
        case FIND: {
            return interpretFind(tokens);
        }
        case TODO: {
            return interpretTodo(tokens);
        }
        case DEADLINE: {
            return interpretDeadline(tokens, dates);
        }
        case EVENT: {
            return interpretEvent(tokens, dates);
        }
        case BYE: {
            return interpretBye();
        }
        default: {
            throw new ActionException();
        }
        }
    }

    private LocalDateTime[] interpretLocalDateTime(String[] tokens) throws TaskException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        LocalDateTime[] dates = new LocalDateTime[tokens.length];
        Arrays.fill(dates, LocalDateTime.MIN);
        try {
            for (int i = 3; i < tokens.length; i++) {
                if (tokens[i].isEmpty()) {
                    break;
                }
                dates[i - 3] = LocalDateTime.parse(tokens[i], formatter);
            }
        } catch (DateTimeParseException e) {
            throw new TaskException();
        }
        return dates;
    }

    private String interpretList() {
        return taskList.toString();
    }

    private String interpretMark(String[] tokens) {
        int taskNum = Integer.parseInt(tokens[2]);
        taskList.mark(taskNum);
        return "Nice! I've marked this task as done:\n\t"
                + taskList.getTask(taskNum);
    }

    private String interpretUnmark(String[] tokens) {
        int taskNum = Integer.parseInt(tokens[2]);
        taskList.unmark(taskNum);
        return "OK, I've marked this task as not done yet:\n\t"
                + taskList.getTask(taskNum);
    }

    private String interpretDelete(String[] tokens) {
        int taskNum = Integer.parseInt(tokens[2]);
        Task removedTask = taskList.deleteTask(taskNum);
        taskList.sortTask();
        return "Noted. I've removed this task:\n"
                + removedTask + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list.";
    }

    private String interpretFind(String[] tokens) {
        ArrayList<Task> tasks = taskList.findTasks(tokens[2]);
        TaskList foundTasks = new TaskList(tasks);
        return "Here are the matching tasks in your list:\n"
                + foundTasks.toString();
    }

    private String interpretTodo(String[] tokens) {
        Task addedTask = taskList.addTodo(tokens);
        taskList.sortTask();
        return "Got it. I've added this task:\n\t"
                + addedTask + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list.";
    }

    private String interpretDeadline(String[] tokens, LocalDateTime[] dates) throws TaskException {
        if (!isValidDateTime(dates)) {
            throw new TaskException();
        }
        Task addedTask = taskList.addDeadline(tokens, dates);
        taskList.sortTask();
        return "Got it. I've added this task:\n\t"
                + addedTask + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list.";
    }

    private String interpretEvent(String[] tokens, LocalDateTime[] dates) throws TaskException {
        if (!isValidDateTime(dates)) {
            throw new TaskException();
        }
        Task addedTask = taskList.addEvent(tokens, dates);
        taskList.sortTask();
        return "Got it. I've added this task:\n\t"
                + addedTask + "\n"
                + "Now you have "
                + taskList.size() + " task(s) in the list.";
    }

    private String interpretBye() {
        return "exit";
    }

    private boolean isValidDateTime(LocalDateTime[] dates) {
        return Arrays.stream(dates)
                .allMatch((localDateTime) -> localDateTime.isAfter(LocalDateTime.now())
                        || localDateTime.isEqual(LocalDateTime.MIN));
    }
}
