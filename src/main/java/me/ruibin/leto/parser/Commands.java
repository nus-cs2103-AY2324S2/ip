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

    private Function<String, Results> commandAction;
    private Commands(Function<String, Results> function) {
        this.commandAction = function;
    }

    public Results run(String inputs) {
        return this.commandAction.apply(inputs);
    }
}
