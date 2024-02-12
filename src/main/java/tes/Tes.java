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
    public void run() {
        this.ui.greet();

        this.parser.parse();
    }
    public static void main(String[] args) {
        Tes chatbot = new Tes();
        chatbot.run();
    }
}
