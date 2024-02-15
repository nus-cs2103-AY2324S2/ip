package simpli.interpreter;

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
        // interpret the string date and time as LocalDateTime object
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

        // interpreting different action types
        Action actionType = Action.valueOf(tokens[0].toUpperCase());
        switch (actionType) {
        case LIST: { // list stored tasks
            return taskList.toString();
        }
        case MARK: { // mark task as done
            int taskNum = Integer.parseInt(tokens[2]);
            taskList.mark(taskNum);
            return "Nice! I've marked this task as done:\n\t"
                    + taskList.getTask(taskNum);
        }
        case UNMARK: { // mark task as undone
            int taskNum = Integer.parseInt(tokens[2]);
            taskList.unmark(taskNum);
            return "OK, I've marked this task as not done yet:\n\t"
                    + taskList.getTask(taskNum);
        }
        case DELETE: { // delete task
            int taskNum = Integer.parseInt(tokens[2]);
            Task removedTask = taskList.deleteTask(taskNum);
            return "Noted. I've removed this task:\n"
                    + removedTask + "\n"
                    + "Now you have " + taskList.size() + " task(s) in the list.";
        }
        case FIND: { // find tasks with the given keyword
            ArrayList<Task> tasks = taskList.findTasks(tokens[2]);
            TaskList foundTasks = new TaskList(tasks);
            return "Here are the matching tasks in your list:\n"
                    + foundTasks.toString();
        }
        case TODO: { // creates todo task
            Task addedTask = taskList.addTodo(tokens);
            return "Got it. I've added this task:\n\t"
                    + addedTask + "\n"
                    + "Now you have " + taskList.size() + " task(s) in the list.";
        }
        case DEADLINE: { // creates deadline task
            if (!isValidDateTime(dates)) {
                throw new TaskException();
            }
            Task addedTask = taskList.addDeadline(tokens, dates);
            return "Got it. I've added this task:\n\t"
                    + addedTask + "\n"
                    + "Now you have " + taskList.size() + " task(s) in the list.";
        }
        case EVENT: { // creates event task
            if (!isValidDateTime(dates)) {
                throw new TaskException();
            }
            Task addedTask = taskList.addEvent(tokens, dates);
            return "Got it. I've added this task:\n\t"
                    + addedTask + "\n"
                    + "Now you have "
                    + taskList.size() + " task(s) in the list.";
        }
        case BYE: {
            return "exit";
        }
        default: {
            throw new ActionException();
        }
        }
    }

    private boolean isValidDateTime(LocalDateTime[] dates) {
        return Arrays.stream(dates)
                .allMatch((localDateTime) -> localDateTime.isAfter(LocalDateTime.now())
                        || localDateTime.isEqual(LocalDateTime.MIN));
    }
}
