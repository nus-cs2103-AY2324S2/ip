package rick.logic;

import rick.logic.command.ByeCommand;
import rick.logic.command.Command;
import rick.logic.command.DeadlineCommand;
import rick.logic.command.DeleteCommand;
import rick.logic.command.EventCommand;
import rick.logic.command.FindCommand;
import rick.logic.command.ListCommand;
import rick.logic.command.MarkCommand;
import rick.logic.command.TodoCommand;
import rick.logic.command.UnmarkCommand;


/**
 * A parser to parse user input.
 */

public class Parser {
    private String input;
    private String type;

    /**
     * Constructor for parser.
     * @param userInput a string from user input
     */
    public Parser(String userInput) throws RickException {
        if (userInput == null || userInput.isEmpty() || userInput.isBlank()) {
            throw new RickException("Wh-what's this, Morty? You send me an empty message? "
                    + "You think this is a joke? I'm not here to play games, Morty. "
                    + "Get it together and send me a proper message, or "
                    + "I'll, I'll... I'll turn you into a pickle, Morty!");
        }
        assert userInput != null && !userInput.isEmpty();
        if (userInput.contains(" ")) {
            String[] splited = userInput.split("\\s+", 2);
            this.type = splited[0];
            this.input = splited[1];
        } else {
            this.type = userInput;
        }
    }

    /**
     * Parse the user input
     * @return the parsed string
     */
    public Command parse() throws RickException {
        assert this.type != null;
        switch (this.type) {
        case ("bye"):
            return new ByeCommand();
        case ("list"):
            return new ListCommand();
        case ("unmark"):
            return new UnmarkCommand(this.input);
        case ("mark"):
            return new MarkCommand(this.input);
        case ("todo"):
            return new TodoCommand(this.input);
        case ("event"):
            return new EventCommand(this.input);
        case ("deadline"):
            return new DeadlineCommand(this.input);
        case ("delete"):
            return new DeleteCommand(this.input);
        case ("find"):
            return new FindCommand(this.input);
        default:
            throw new RickException("Wh-what's that, Morty? You're talking gibberish again. "
                    + "I can't understand a word you're saying. "
                    + "Maybe lay off the... the... whatever you're drinking, Morty. Jeez.");
        }
    }
}
