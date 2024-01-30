package duke.util;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readUserInput() {
        return sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm KAI\n" + "Please type in what you want to do");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showFarewell() {
        sc.close();
        System.out.println("Bye Bye. Hope to see you again soon!");
    }
}
