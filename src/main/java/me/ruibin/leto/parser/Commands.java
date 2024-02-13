package me.ruibin.leto.parser;

import java.util.function.Function;

enum Commands {
    BYE(new ByeCommand()),
    EXIT(new ByeCommand()),
    QUIT(new ByeCommand()),
    LIST(new ListCommand()),
    TODO(new NewTaskCommand()),
    EVENT(new NewTaskCommand()),
    DEADLINE(new NewTaskCommand()),
    MARK(new MarkCommand()),
    UNMARK(new UnmarkCommand()),
    DELETE(new DeleteCommand()),
    SAVE(new SaveCommand()),
    HELP(new HelpCommand()),
    DEFAULT(new HelpCommand()),
    FIND(new FindCommand());

    private final Function<String, Result> commandAction;
    private Commands(Function<String, Result> function) {
        this.commandAction = function;
    }

    /** Run the specific command. */
    public Result run(String inputs) {
        return this.commandAction.apply(inputs);
    }
}
