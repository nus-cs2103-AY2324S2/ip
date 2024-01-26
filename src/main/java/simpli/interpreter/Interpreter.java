package simpli.interpreter;

import simpli.actions.Action;
import simpli.core.Simpli;
import simpli.exceptions.ActionException;
import simpli.exceptions.TaskException;

public class Interpreter {
    private final Simpli app;

    public Interpreter(Simpli app) {
        this.app = app;
    }

    public void interpret(String[] tokens) throws ActionException {
        Simpli app = this.app;
        Action actionType = Action.valueOf(tokens[0].toUpperCase());
        switch (actionType) {
            case Action.LIST:  // list stored tasks
                app.list();
                break;
            case Action.MARK: {  // mark task as done
                if (tokens[1].isEmpty()) {
                    throw new ActionException();
                }
                app.mark(Integer.parseInt(tokens[1]));
                break;
            }
            case Action.UNMARK: {  // mark task as undone
                if (tokens[1].isEmpty()) {
                    throw new ActionException();
                }
                app.unmark(Integer.parseInt(tokens[1]));
                break;
            }
            case Action.DELETE: {  // delete task
                if (tokens[1].isEmpty()) {
                    throw new ActionException();
                }
                app.deleteTask(Integer.parseInt(tokens[1]));
                break;
            }
            case Action.TODO: {  // creates todo task
                if (tokens[1].isEmpty()) {
                    throw new TaskException();
                }
                app.addTodo(tokens[1]);
                break;
            }
            case Action.DEADLINE: {  // creates deadline task
                if (tokens[1].isEmpty() || !tokens[2].equals("by") || tokens[3].isEmpty()) {
                    throw new TaskException();
                }
                app.addDeadline(tokens[1], tokens[3]);
                break;
            }
            case Action.EVENT: {  // creates event task
                if (tokens[1].isEmpty() || !tokens[2].equals("from") || tokens[3].isEmpty() ||
                        !tokens[4].equals("to") || tokens[5].isEmpty()) {
                    throw new TaskException();
                }
                app.addEvent(tokens[1], tokens[3], tokens[5]);
                break;
            }
            default: {
                throw new ActionException();
            }
        }
    }
}
