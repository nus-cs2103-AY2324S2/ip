package Duke.phrase;

import Duke.Commands.*;
import Duke.Commands.Unknow;
import Duke.Exception.CommandException;

public class phrase {
    public static Command phraseCommand(String input) throws CommandException {
        String firstWord = input.split(" ")[0];
        int trimIndex = firstWord.length();
        String remainingWord = input.substring(trimIndex).trim();
        switch (firstWord) {
            case "todo":
                return new AddTodo(remainingWord);
            case "deadline":
                return new AddDeadline(remainingWord);
            case "event":
                return new AddEvent(remainingWord);
            case "list":
                return new ListActivity(remainingWord);
            case "mark":
                return new MarkActivity(remainingWord);
            case "unmark":
                return new UnmarkActivity(remainingWord);
            case "find":
                return new FindActivity(remainingWord);
            case "delete":
                return new DeleteActivity(remainingWord);
            case "bye":
                return new Terminate(remainingWord);
            default:
                return new Unknow("I'm sorry, but I don't know what that means");
        }
    }

}
