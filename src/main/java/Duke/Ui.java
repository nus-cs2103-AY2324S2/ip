package duke;

import java.util.Scanner;

public class Ui {

    public Ui() {
    }

    public void run() {
        greet();
        scan();
    }

    public void greet() {
        System.out.println(" /\\_/\\");
        System.out.println("((@v@))");
        System.out.println("():::()");
        System.out.println(" VV-VV");
        System.out.println("What can I do for you?");
    }

    public void scan() {
        CommandHandler commandHandler = new CommandHandler(this);
        Scanner scanner = new Scanner(System.in);

        boolean isExitScan = false;
        while (!isExitScan) {
            String userInput = scanner.nextLine();
            try {
                isExitScan = commandHandler.executeCommand(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}