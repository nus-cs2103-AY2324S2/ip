package duke;

import duke.ExitCommand;
import duke.IncorrectCommand;

public class Parser {

    public Command parse(String input) {
        String[] words = input.split("\\s+");
        String command = words[0];
        switch (command) {
        case "todo":
        case "deadline":
        case "event":
        case "list":
        case "mark":
        case "unmark":
        case "delete":
            return new Command(command, input);
        case "bye":
            System.out.println(command);
            return new ExitCommand();
        default:
            return new IncorrectCommand();
        }
    }


}
