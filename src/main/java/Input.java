import java.util.Scanner;
public class Input {

    Scanner sc;
    Command currentCommand;

    public Input() {
        this.sc = new Scanner(System.in);
    }

    public Command getCommand(String str) {
        switch (str) {
        case "bye":
            this.currentCommand = Command.BYE;
            break;
        case "mark":
            this.currentCommand = Command.MARK;
            break;
        case "unmark":
            this.currentCommand = Command.UNMARK;
            break;
        case "delete":
            this.currentCommand = Command.DELETE;
            break;
        case "list":
            this.currentCommand = Command.LIST;
            break;
        case "event":
            this.currentCommand = Command.EVENT;
            break;
        case "deadline":
            this.currentCommand = Command.DEADLINE;
            break;
        case "todo":
            this.currentCommand = Command.TODO;
            break;
        default:
            this.currentCommand = Command.UNKNOWN;
            break;

        }
        return currentCommand;

    }

    public String getNextLine() {
        return this.sc.nextLine();
    }

    public String getCommandString(String input) {
        String[] arr = input.split(" ", 2);
        String command = arr[0].toLowerCase();
        return command;
    }


}
