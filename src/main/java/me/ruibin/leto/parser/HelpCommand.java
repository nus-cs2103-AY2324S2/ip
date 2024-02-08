package me.ruibin.leto.parser;

import me.ruibin.leto.ui.Ui;

import java.util.function.Function;

/** Prints help message and returns <code>Results.OK</code>*/
public class HelpCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        Ui.letoHelp();
        return Results.OK;
    }
}
