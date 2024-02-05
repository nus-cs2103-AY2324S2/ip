package handler;

import action.task.Task;

import java.util.ArrayList;

public final class PrintHandler {
    public static final PrintHandler instance = new PrintHandler();
    private static final String DIVIDER = "------------------------------------";
    private PrintHandler() {}

    public void print(String msg) {
        System.out.println(msg);
    }
    public void printWithDivider(String msg) {
        System.out.println(msg);
        System.out.println(DIVIDER);
    }
    public void printNumberedDivider(ArrayList<Task> msgs) {
        for (int i = 0; i < msgs.size(); i++) {
            int index = i + 1;
            System.out.println(Integer.toString(index) + ". " + msgs.get(i));
        }
        System.out.println(DIVIDER);
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
        System.out.println(DIVIDER);
    }


}
