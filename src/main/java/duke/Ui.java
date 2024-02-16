package duke;

import java.util.Scanner;
public class Ui {

    Scanner sc;
    Command currentCommand;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void hello() {
        System.out.println("Hello!");
        System.out.println("What can I do for you?");
    }

    public Command getCommand(String str) {
        String cmdStr = getCommandString(str);

        switch (cmdStr) {
        case "b":
        case "bye":
            this.currentCommand = Command.BYE;
            break;
        case "m":
        case "mark":
            this.currentCommand = Command.MARK;
            break;
        case "u":
        case "unmark":
            this.currentCommand = Command.UNMARK;
            break;
        case "del":
        case "delete":
            this.currentCommand = Command.DELETE;
            break;
        case "ls":
        case "list":
            this.currentCommand = Command.LIST;
            break;
        case "e":
        case "event":
            this.currentCommand = Command.EVENT;
            break;
        case "d":
        case "deadline":
            this.currentCommand = Command.DEADLINE;
            break;
        case "t":
        case "todo":
            this.currentCommand = Command.TODO;
            break;
        case "cl":
        case "clear":
            this.currentCommand = Command.CLEAR;
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
