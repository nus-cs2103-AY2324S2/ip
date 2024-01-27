package fileIO;

import static constants.Information.solidLineBreak;
import static constants.Messages.BYE_MESSAGE;
import static constants.Messages.START_MESSAGE;

public class PrintOutputs {
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
}
