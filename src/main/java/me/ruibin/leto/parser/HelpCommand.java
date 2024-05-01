package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.ui.Ui;

/** Prints help message and returns <code>Result</code> of type <code>ResultTypes.OK</code>*/
public class HelpCommand implements Function<String, Result> {
    @Override
    public Result apply(String s) {
        return Result.makeResultOk(Ui.letoHelp());
    }
}
