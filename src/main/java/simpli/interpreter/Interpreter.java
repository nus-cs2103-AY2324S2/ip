package simpli.interpreter;

import simpli.tasks.TaskManager;
import simpli.ui.Ui;
import simpli.actions.Action;
import simpli.exceptions.ActionException;
import simpli.tasks.Task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Interpreter {
    private Ui ui;
    private TaskManager taskManager;

    public Interpreter(Ui ui, TaskManager taskManager) {
        this.ui = ui;
        this.taskManager = taskManager;
    }

    public void interpret(String[] tokens) throws ActionException {
        // interpret the string date and time as LocalDateTime object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime[] dates = new LocalDateTime[tokens.length];
        Arrays.fill(dates, LocalDateTime.MIN);
        for (int i = 3; i < tokens.length; i++) {
            if (tokens[i].isEmpty()) {
                break;
            }
            dates[i - 3] = LocalDateTime.parse(tokens[i], formatter);
        }

        // interpreting different action types
        Action actionType = Action.valueOf(tokens[0].toUpperCase());
        switch (actionType) {
        case Action.LIST:  // list stored tasks
            ui.display(taskManager.toString());
            break;
        case Action.MARK: {  // mark task as done
            int taskNum = Integer.parseInt(tokens[2]);
            taskManager.mark(taskNum);
            ui.display("Nice! I've marked this task as done:\n\t" +
                    taskManager.getTask(taskNum));
            break;
        }
        case Action.UNMARK: {  // mark task as undone
            int taskNum = Integer.parseInt(tokens[2]);
            taskManager.unmark(taskNum);
            ui.display("OK, I've marked this task as not done yet:\n\t" +
                    taskManager.getTask(taskNum));
            break;
        }
        case Action.DELETE: {  // delete task
            int taskNum = Integer.parseInt(tokens[2]);
            Task removedTask = taskManager.deleteTask(taskNum);
            ui.display("Noted. I've removed this task:\n" +
                    removedTask + "\n" +
                    "Now you have " + taskManager.size() + " task(s) in the list.");
            break;
        }
        case Action.TODO: {  // creates todo task
            Task addedTask = taskManager.addTodo(tokens);
            ui.display("Got it. I've added this task:\n\t" +
                    addedTask + "\n" +
                    "Now you have " + taskManager.size() + " task(s) in the list.");
            break;
        }
        case Action.DEADLINE: {  // creates deadline task
            Task addedTask = taskManager.addDeadline(tokens, dates);
            ui.display("Got it. I've added this task:\n\t" +
                    addedTask + "\n" +
                    "Now you have " + taskManager.size() + " task(s) in the list.");
            break;
        }
        case Action.EVENT: {  // creates event task
            Task addedTask = taskManager.addEvent(tokens, dates);
            ui.display("Got it. I've added this task:\n\t" +
                    addedTask + "\n" +
                    "Now you have " + taskManager.size() + " task(s) in the list.");
            break;
        }
        default: {
            throw new ActionException();
        }
        }
    }
}
