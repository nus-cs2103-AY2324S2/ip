package handler;

import action.task.Task;
import enums.SavedString;

import java.util.ArrayList;

public final class PrintHandler {
    private static final String DIVIDER = "------------------------------------";
    private PrintHandler() {}

    public static void print(String msg) {
        System.out.println(msg);
    }

    public static void printWithDivider(String msg) {
        print(msg);
        print(DIVIDER);
    }
    public static void printNumberedDivider(ArrayList<Task> msgs) {
        for (int i = 0; i < msgs.size(); i++) {
            int index = i + 1;
            print(index + ". " + msgs.get(i));
        }
        print(DIVIDER);
    }
    public static void printException(Exception e) {
        printWithDivider(e.getMessage());
    }
    public static void printInit() {
        printWithDivider(SavedString.LOGO.getContent());
        printWithDivider(SavedString.GREETINGS.getContent());
    }
}
