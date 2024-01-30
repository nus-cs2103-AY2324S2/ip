package ui;

import task.Task;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "  ---------------------------------------------------------------------------------------";
    private static final String BOTNAME = "TOBIAS";

    /**
     * Prints the welcome message of Tobias in console.
     * */
    public void helloPrinter() {
        System.out.println(DIVIDER);
        System.out.println("   Hello there! I'm " + BOTNAME);
        System.out.println("   What can I do for you today ?");
        System.out.println(DIVIDER);
    }


    /**
     * Prints the farewell message of Tobias.
     * */
    public void goodbyePrinter() {
        System.out.println(DIVIDER);
        System.out.println("    I say this with a heavy heart but Goodbye my lover :( Hope to see you soon!");
        System.out.println(DIVIDER);
    }


    /**
     * Prints the task given and the total number of tasks in the list.
     *
     * @param task Task that is provided.
     * @param size Total number of tasks in the list now.
     * */
    public void addedTaskPrinter(Task task, int size) {
        System.out.println(DIVIDER);
        System.out.println("    Got it. I've added this task: ");
        task.taskPrinter();
        System.out.println("    Now you have " + size + " tasks in the list!");
        System.out.println(DIVIDER);
    }

    /**
     * Prints a divider.
     * */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Takes in the input given by user and returns it as a String
     *
     * @return String command of user input.
     * */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().trim();
        return command;
    }
}
