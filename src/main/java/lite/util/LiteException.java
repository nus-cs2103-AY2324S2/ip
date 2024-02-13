package lite.util;

import java.io.IOException;

public class LiteException {
    public static void InvalidInput() {
        Printer.printHorizontalLine();
        System.out.println("Invalid input .\n" +
                "Please begin your input with either one of these keywords: \n" +
                "todo, list, deadline, event, mark, unmark, bye");
        Printer.printHorizontalLine();
    }

    public static void LoadFileException() {
        Printer.printHorizontalLine();
        System.out.println("Unable to load data from local file");
        System.out.println("File may be corrupted");
        Printer.printHorizontalLine();
    }

    public static void SaveException(IOException e) {
        Printer.printHorizontalLine();
        System.out.println("Failed to save to a local file");
        System.out.println(e);
        Printer.printHorizontalLine();
    }
}
