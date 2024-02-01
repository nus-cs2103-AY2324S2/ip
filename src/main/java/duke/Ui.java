package duke;

import java.util.Scanner;

public class Ui {

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

    public Ui() {

    }

    public void printWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("      Hello! I'm AndrewOng2066");
        System.out.println("      What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    public void printByeStatement() {
        System.out.println("      Bye. Hope to see you again soon!");
    }


    public void printAnyStatement(String input) {
        System.out.println("    " + input);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }


    public void printOpeningDottedLine() {
        System.out.println("    ____________________________________________________________");
    }


    public void printClosingDottedLine() {
        System.out.println("    ____________________________________________________________\n");
    }


    public void printInvalidFeature() {
        System.out.println("      I'm sorry, I do not understand that.");
    }


    public void printLoadingError() {
        System.out.println("    Unable to load the data from the file.");
    }

    public void printError(String e) {
        System.out.println("    " + e);
    }

}
