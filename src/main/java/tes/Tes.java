package tes;

import tes.command.Parser;
import tes.command.Ui;

/**
 * Represents the chatbot.
 */
public class Tes {
    /** User interface to deal with input and output */
    private Ui ui;
    /** tes.command.Parser to deal with commands */
    private Parser parser;

    /**
     * Constructs a chatbot.
     */
    public Tes() {
        this.ui = new Ui();
        this.parser = new Parser(ui);
    }

    /**
     * Gets response according to the command given.
     *
     * @param command Command given by the user.
     */
    public String getResponse(String command) {
        return this.parser.parse(command);
    }

    /**
     * Greets the user when initializing the chatbot.
     */
    public String greet() {
        return this.ui.greet();
    }

    /**
     * Closes the chatbot and stop running the program.
     */
    public String exit() {
        return this.ui.close();
    }

}
