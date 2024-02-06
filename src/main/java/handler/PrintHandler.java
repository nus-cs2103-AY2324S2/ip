package handler;

import action.task.Task;

import java.util.ArrayList;

public final class PrintHandler {
    private static final String DIVIDER = "------------------------------------";
    private PrintHandler() {}

    public static void print(String msg) {
        System.out.println(msg);
    }
    public static void printWithDivider(String msg) {
        System.out.println(msg);
        System.out.println(DIVIDER);
    }
    public static void printNumberedDivider(ArrayList<Task> msgs) {
        for (int i = 0; i < msgs.size(); i++) {
            int index = i + 1;
            System.out.println(Integer.toString(index) + ". " + msgs.get(i));
        }
        System.out.println(DIVIDER);
    }

    public static void printException(Exception e) {
        System.out.println(e.getMessage());
        System.out.println(DIVIDER);
    }


}
