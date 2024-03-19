package lilybot;

import lilybot.Command.*;
import lilybot.Gui.Ui;
import lilybot.Parser.Parser;
import lilybot.Storage.Storage;
import lilybot.Task.TaskList;

import java.io.IOException;


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
    public LilyBot(String filePath) throws IOException{
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
    public String getResponse(String input, String lastCommand) throws IOException{
        String[] inputBySpace = Parser.parseCommand(input);
        String firstWord = inputBySpace[0].toUpperCase();
        Command command;
        switch (firstWord) {
        case "LIST":
            command = new ListCommand(ui, input, ls);
            return command.exceute(ui, input, ls);
        case "TODO":
            command = new TodoCommand(ui, input, ls);
            return command.exceute(ui, input, ls);
        case "DEADLINE":
            command = new DeadlineCommand(ui, input, ls);
            return command.exceute(ui, input, ls);
        case "EVENT":
            command = new EventCommand(ui, input, ls);
            return command.exceute(ui, input, ls);
        case "MARK":
            command = new MarkCommand(ui, input, ls);
            return command.exceute(ui, input, ls);
        case "UNMARK":
            command = new UnmarkCommand(ui, input, ls);
            return command.exceute(ui, input, ls);
        case "DELETE":
            command = new DeleteCommand(ui, input, ls);
            return command.exceute(ui, input, ls);
        case "UNDO":
            command = new UndoCommand(ui, lastCommand, ls);
            return command.exceute(ui, lastCommand, ls);
        case "FIND":
            command = new FindCommand(ui, input, ls);
            return command.exceute(ui, input, ls);
        case "BYE":
            ByeCommand bye = new ByeCommand(storage, ui, input, ls);
            return bye.exceute(storage, ui, input, ls);
        default:
            return ui.invalidInput();

        }

    }







}
