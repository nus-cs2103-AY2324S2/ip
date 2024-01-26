package ui;

import task.Task;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "  ---------------------------------------------------------------------------------------";
    private static final String BOTNAME = "TOBIAS";
    public void helloPrinter() {
        System.out.println(DIVIDER);
        System.out.println("   Hello there! I'm " + BOTNAME);
        System.out.println("   What can I do for you today ?");
        System.out.println(DIVIDER);
    }

    public void goodbyePrinter() {
        System.out.println(DIVIDER);
        System.out.println("    I say this with a heavy heart but Goodbye my lover :( Hope to see you soon!");
        System.out.println(DIVIDER);
    }

    public void addedTaskPrinter(Task task, int size) {
        System.out.println(DIVIDER);
        System.out.println("    Got it. I've added this task: ");
        task.taskPrinter();
        System.out.println("    Now you have " + size + " tasks in the list!");
        System.out.println(DIVIDER);
    }

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().trim();
        return command;
    }
}
