package lex.parser;

import lex.parser.command.ByeCommand;
import lex.parser.command.Command;
import lex.parser.command.DeadlineCommand;
import lex.parser.command.DeleteCommand;
import lex.parser.command.EventCommand;
import lex.parser.command.FindCommand;
import lex.parser.command.HelpCommand;
import lex.parser.command.ListCommand;
import lex.parser.command.MarkCommand;
import lex.parser.command.TodoCommand;
import lex.parser.command.UnknownCommand;
import lex.parser.command.UnmarkCommand;
import lex.storage.Storage;
import lex.tasks.TaskList;
import lex.ui.Ui;

/**
 * Represents a parser to parse user input.
 */
public class Parser {
    private final TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructor for the Parser class.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The corresponding command.
     */
    public Command parse(String input) {
        String[] inputs = input.split(" ", 2);
        Instruction instruction;

        try {
            instruction = Instruction.valueOf(inputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return new UnknownCommand(ui);
        }

        switch (instruction) {
        case LIST:
            return new ListCommand(tasks, ui);
        case TODO:
            return new TodoCommand(inputs, tasks, ui);
        case DEADLINE:
            return new DeadlineCommand(inputs, tasks, ui);
        case EVENT:
            return new EventCommand(inputs, tasks, ui);
        case MARK:
            return new MarkCommand(inputs, tasks, ui);
        case UNMARK:
            return new UnmarkCommand(inputs, tasks, ui);
        case DELETE:
            return new DeleteCommand(inputs, tasks, ui);
        case FIND:
            return new FindCommand(inputs, tasks, ui);
        case HELP:
            return new HelpCommand(ui);
        case BYE:
            return new ByeCommand(tasks, ui, storage);
        default:
            return new UnknownCommand(ui);
        }
    }
}
