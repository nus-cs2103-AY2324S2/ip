package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.ui.Ui;

/** Prints help message and returns <code>Results.OK</code>*/
public class HelpCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        Ui.letoHelp();
        return Results.OK;
    }
}
