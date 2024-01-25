package simpli.interpreter;

import simpli.core.Simpli;
import simpli.exceptions.ActionException;
import simpli.exceptions.TaskException;

public class Interpreter {
    private final Simpli simpli;
    public Interpreter() {
        this.simpli = new Simpli();
    }
    public void interpret(String[] tokens) throws ActionException {
        switch (tokens[0]) {
            case "list":  // list stored tasks
                this.simpli.list();
                break;
            case "mark": {  // mark task as done
                if (tokens[1].isEmpty()) {
                    throw new ActionException();
                }
                this.simpli.mark(Integer.parseInt(tokens[1]));
                break;
            }
            case "unmark": {  // mark task as undone
                if (tokens[1].isEmpty()) {
                    throw new ActionException();
                }
                this.simpli.unmark(Integer.parseInt(tokens[1]));
                break;
            }
            case "delete": {  // delete task
                if (tokens[1].isEmpty()) {
                    throw new ActionException();
                }
                this.simpli.deleteTask(Integer.parseInt(tokens[1]));
                break;
            }
            case "todo": {  // creates todo task
                if (tokens[1].isEmpty()) {
                    throw new TaskException();
                }
                this.simpli.addTodo(tokens[1]);
                break;
            }
            case "deadline": {  // creates deadline task
                if (tokens[1].isEmpty() || !tokens[2].equals("by") || tokens[3].isEmpty()) {
                    throw new TaskException();
                }
                this.simpli.addDeadline(tokens[1], tokens[3]);
                break;
            }
            case "event": {  // creates event task
                if (tokens[1].isEmpty() || !tokens[2].equals("from") || tokens[3].isEmpty() ||
                        !tokens[4].equals("to") || tokens[5].isEmpty()) {
                    throw new TaskException();
                }
                this.simpli.addEvent(tokens[1], tokens[3], tokens[5]);
                break;
            }
            default: {
                throw new ActionException();
            }
        }
    }
}
