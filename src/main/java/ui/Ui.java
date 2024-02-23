package ui;

import java.util.Scanner;

public class Ui {
    public static void showGreeting() {
        String name = "Cal";
        String logo = "  ____     _     _ \n"
                    +" / ___|   / \\   | | \n"
                    +"| |      / - \\  | | \n"
                    +"| |___  / --- \\ | |___ \n"
                    +(" \\____|/_/   \\_\\|_____|");
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(logo);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand(Scanner sc) {
        String input = sc.nextLine().toLowerCase();
        return input;
    }

    public void showErrorMsg(Exception e) {
        System.out.println(e.getMessage());
    }
}
