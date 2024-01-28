package bytebuddy.ui;

import java.util.Scanner;

import static bytebuddy.constants.Information.solidLineBreak;
import static bytebuddy.constants.Messages.BYE_MESSAGE;
import static bytebuddy.constants.Messages.START_MESSAGE;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static void printWithSolidLineBreak(String s) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t " + s);
        System.out.println("\t" + solidLineBreak);
    }

    public static void printStartMessage() {
        printWithSolidLineBreak(START_MESSAGE);
    }

    public static void printByeMessage() {
        printWithSolidLineBreak(BYE_MESSAGE);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}

