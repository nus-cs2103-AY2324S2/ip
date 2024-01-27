import java.util.Scanner;

public class Parser {
    private Scanner scanner;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    public Command parseCommand() {
        String line = this.scanner.nextLine();
        String[] curCommand = line.split(" ");
        switch (curCommand[0]) {
        case "list":
            return new ListCommand(curCommand);
        case "mark":
            // Fallthrough
        case "unmark":
            return new ModifyMarkCommand(curCommand);
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            return new TaskCommand(curCommand);
        case "delete":
            return new DeleteCommand(curCommand);
        case "save":
            return new SaveCommand(curCommand);
        case "bye":
            return new ByeCommand(curCommand);
        default:
            return new InvalidCommand(curCommand);
        }
    }
}
