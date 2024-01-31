package toothless;

import toothless.commands.*;

public class Parser {

    public static Command parseCommand(String input) throws ToothlessException {
        String[] split = input.split(" ", 2);
        switch (split[0].toUpperCase()) {
        case "BYE":
            return new ByeCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            if (split.length < 2){
                throw new ToothlessException("Input number pls");
            }
            return new MarkCommand(split[1]);
        case "UNMARK":
            if (split.length < 2){
                throw new ToothlessException("Input number pls");
            }
            return new UnmarkCommand(split[1]);
        case "DELETE":
            if (split.length < 2){
                throw new ToothlessException("Input number pls");
            }
            return new DeleteCommand(split[1]);
        case "TODO":
            if (split.length < 2){
                throw new ToothlessException("Input description @_@");
            }
            return new TodoCommand(split[1]);
        case "EVENT":
            if (split.length < 2){
                throw new ToothlessException("Input description @_@");
            }
            return new EventCommand(split[1]);
        case "DEADLINE":
            if (split.length < 2){
                throw new ToothlessException("Input description @_@");
            }
            return new DeadlineCommand(split[1]);
        default:
            throw new ToothlessException("Me dragon, no understand this action :P");
        }
    }
}
