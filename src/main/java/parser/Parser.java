package parser;

import command.Command;
import duke.Duke;

public class Parser {
    public static Command parseCommand(String userInput) {
        String[] words = userInput.split(" ");
        String commandWord = words[0].toLowerCase();

        switch (commandWord) {
            case "todo":
                return new Command(words, Duke.CommandType.TODO);
            case "deadline":
                return new Command(words, Duke.CommandType.DEADLINE);
            case "event":
                return new Command(words, Duke.CommandType.EVENT);
            case "list":
                return new Command(words, Duke.CommandType.LIST);
            case "mark":
                return new Command(words, Duke.CommandType.MARK);
            case "unmark":
                return new Command(words, Duke.CommandType.UNMARK);
            case "delete":
                return new Command(words, Duke.CommandType.DELETE);
            case "bye":
                return new Command(words, Duke.CommandType.BYE);
            default:
                return new Command(words, Duke.CommandType.UNKNOWN);
        }
    }
}
