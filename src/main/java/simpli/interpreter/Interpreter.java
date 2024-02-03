package simpli.interpreter;

import simpli.actions.Action;
import simpli.core.Simpli;
import simpli.exceptions.ActionException;
import simpli.exceptions.TaskException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Interpreter {
    private final Simpli app;

    public Interpreter(Simpli app) {
        this.app = app;
    }

    public void interpret(String[] tokens) throws ActionException {
        Simpli app = this.app;

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

        Action actionType = Action.valueOf(tokens[0].toUpperCase());
        switch (actionType) {
        case Action.LIST:  // list stored tasks
            app.list();
            break;
        case Action.MARK: {  // mark task as done
            app.mark(Integer.parseInt(tokens[2]));
            break;
        }
        case Action.UNMARK: {  // mark task as undone
            app.unmark(Integer.parseInt(tokens[2]));
            break;
        }
        case Action.DELETE: {  // delete task
            app.deleteTask(Integer.parseInt(tokens[2]));
            break;
        }
        case Action.TODO: {  // creates todo task
            app.addTodo(tokens);
            break;
        }
        case Action.DEADLINE: {  // creates deadline task
            app.addDeadline(tokens, dates);
            break;
        }
        case Action.EVENT: {  // creates event task
            app.addEvent(tokens, dates);
            break;
        }
        }
    }
}
