package simpli;

public class Interpreter {
    private final Simpli simpli;
    public Interpreter() {
        this.simpli = new Simpli();
    }
    public void execute(String[] tokens) {
        switch (tokens[0]) {
            case "list":  // list stored tasks
                this.simpli.list();
                break;
            case "mark": {  // mark task as done
                this.simpli.mark(Integer.parseInt(tokens[1]));
                break;
            }
            case "unmark": {  // mark task as undone
                this.simpli.unmark(Integer.parseInt(tokens[1]));
                break;
            }
            case "todo": {  // creates todo task
                this.simpli.addTodo(tokens[1]);
                break;
            }
            case "deadline": {  // creates deadline task
                this.simpli.addDeadline(tokens[1], tokens[3]);
                break;
            }
            case "event": {  // creates event task
                this.simpli.addEvent(tokens[1], tokens[3], tokens[5]);
                break;
            }
            default: {
//                    throw CommandException();
                this.simpli.respond("Unable to simp for you :(");
                break;
            }
        }
    }
}
