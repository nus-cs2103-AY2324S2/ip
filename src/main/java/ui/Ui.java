package ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
    
    public void printMessage(String message) {
        System.out.println("\t" + message);
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }
    
    public void printWelcome() {
        printLine();
        String introMessage = " /\\_/\\\n" +
                "\t( o.o )\n" +
                "\t > ^ <\n" +
                "\tNya-ice to meet you! I'm Toothless :D\n" +
                "\tWhat can I do for you?";
        printMessage(introMessage);
        printLine();
    }
}
