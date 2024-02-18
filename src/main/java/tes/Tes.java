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
     * Initializes the chatbot.
     */


    public String getResponse(String command) {
        return this.parser.parse(command);
    }

    public String greet() {
        return this.ui.greet();
    }

    public String exit() {
        return this.ui.close();
    }

}
