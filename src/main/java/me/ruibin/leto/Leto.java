package me.ruibin.leto;

import me.ruibin.leto.parser.Parser;
import me.ruibin.leto.parser.Result;
import me.ruibin.leto.ui.Ui;

/** Main program for Leto. Singleton class. */
public class Leto {
    private static final Leto leto = new Leto();

    private Leto() {
        Ui.letoLogo();
    }

    public static Leto getLeto() {
        return leto;
    }

    public static void main(String[] args) {
        Parser.readExecuteLoop();
    }

    /**
     * Returns Result from parsing and reading user's input.
     */
    public Result getResponse(String inputs) {
        return Parser.readAndExecute(inputs);
    }
}

