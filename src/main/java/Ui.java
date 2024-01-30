import common.Messages;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("\t ____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("\t Hello! I'm JeromeGPT");
        System.out.println("\t What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("\t " + Messages.MESSAGE_GOODBYE);
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }


}
