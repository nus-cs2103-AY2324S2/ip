package lilybot;

import java.io.IOException;

import lilybot.command.ByeCommand;
import lilybot.command.Command;
import lilybot.command.DeadlineCommand;
import lilybot.command.DeleteCommand;
import lilybot.command.EventCommand;
import lilybot.command.FindCommand;
import lilybot.command.ListCommand;
import lilybot.command.MarkCommand;
import lilybot.command.TodoCommand;
import lilybot.command.UndoCommand;
import lilybot.command.UnmarkCommand;
import lilybot.gui.Ui;
import lilybot.parser.Parser;
import lilybot.storage.Storage;
import lilybot.task.TaskList;



/**
 * Creates a LilyBot with LilyBot class.
 */
public class LilyBot {

    private Storage storage;
    private TaskList ls;
    private Ui ui;


    /**
     * Constructs LilyBot with the following constructor.
     *
     * @param filePath For file duke.txt.
     * @throws IOException For input error.
     */
    public LilyBot(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        ls = new TaskList(storage.loadFile());
    }


    /**
     * Gets responses from the bot.
     *
     * @param input Command entered by the user.
     * @param lastCommand Previous command entered by the user.
     * @return What should be displayed for the user based on the command.
     * @throws IOException For input error.
     */
    public String getResponse(String input, String lastCommand) throws IOException {
        String[] inputBySpace = Parser.parseCommand(input);
        String firstWord = inputBySpace[0].toUpperCase();
        Command command;
        switch (firstWord) {
        case "LIST":
            command = new ListCommand(ui, ls);
            return command.exceute(input);
        case "TODO":
            command = new TodoCommand(ui, ls);
            return command.exceute(input);
        case "DEADLINE":
            command = new DeadlineCommand(ui, ls);
            return command.exceute(input);
        case "EVENT":
            command = new EventCommand(ui, ls);
            return command.exceute(input);
        case "MARK":
            command = new MarkCommand(ui, ls);
            return command.exceute(input);
        case "UNMARK":
            command = new UnmarkCommand(ui, ls);
            return command.exceute(input);
        case "DELETE":
            command = new DeleteCommand(ui, ls);
            return command.exceute(input);
        case "UNDO":
            command = new UndoCommand(ui, ls);
            return command.exceute(lastCommand);
        case "FIND":
            command = new FindCommand(ui, ls);
            return command.exceute(input);
        case "BYE":
            ByeCommand bye = new ByeCommand(storage, ui, ls);
            return bye.exceute(input);
        default:
            return ui.invalidInput();

        }

    }







}
