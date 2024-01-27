package duke;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Translate text to command.
 */
class Parser {
    private TaskList tasks;

    /**
     * Constructor of a parse
     * @param tasks tasks
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * From input to determine which type of command to generate
     * @param input scanner's result
     * @throws DukeException wrong usage
     * @return the generated command for execute
     */
    public Command parse(String input) throws DukeException{
        String[] inputs = input.split(" ");
        if (input.equals("bye") || input.equals("exit") || input.equals("quit")) {
            return new Bye();
        } else if (input.equals("list") || input.equals("ls")) {
            return new List(tasks);
        } else if (input.equals("current") || input.equals("curr")) {
            return new CurrentTask(tasks);
        } else if (inputs[0].equals("mark") && inputs.length == 2) {
            return new Mark(Integer.parseInt(inputs[1])-1,tasks);
        } else if (inputs[0].equals("unmark") && inputs.length == 2) {
            return new Unmark(Integer.parseInt(inputs[1])-1,tasks);
        } else if ((inputs[0].equals("delete") || inputs[0].equals("remove")) && inputs.length == 2) {
            return new Delete(Integer.parseInt(inputs[1])-1,tasks);
        } else {
            return new Add(input,tasks);
        }
    }
}
