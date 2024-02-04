package duke;

import duke.command.Add;
import duke.command.Bye;
import duke.command.Command;
import duke.command.CurrentTask;
import duke.command.Delete;
import duke.command.Find;
import duke.command.List;
import duke.command.Mark;
import duke.command.Unmark;
import duke.exception.DukeException;

/**
 * Translate text to command.
 */
class Parser {
    private static final int TOKEN_NUM = 2;
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
     * @return the generated command for execute
     * @throws DukeException wrong usage
     */
    public Command parse(String input) throws DukeException {
        String[] inputs = input.split(" ");
        assert inputs.length > 0 : "at least should have some commands";
        if (input.equals("bye") || input.equals("exit") || input.equals("quit")) {
            return new Bye();
        } else if (input.equals("list") || input.equals("ls")) {
            return new List(tasks);
        } else if (input.equals("current") || input.equals("curr")) {
            return new CurrentTask(tasks);
        } else if (inputs[0].equals("find") && inputs.length == TOKEN_NUM) {
            return new Find(inputs[1], tasks);
        } else if (inputs[0].equals("mark") && inputs.length == TOKEN_NUM) {
            return new Mark(Integer.parseInt(inputs[1]) - 1, tasks);
        } else if (inputs[0].equals("unmark") && inputs.length == TOKEN_NUM) {
            return new Unmark(Integer.parseInt(inputs[1]) - 1, tasks);
        } else if ((inputs[0].equals("delete") || inputs[0].equals("remove")) && inputs.length == TOKEN_NUM) {
            return new Delete(Integer.parseInt(inputs[1]) - 1, tasks);
        } else {
            return new Add(input, tasks);
        }
    }
}
