package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

public class Ui {
    public static final String INDENTATION = " ".repeat(3);
    public static final String SUBIDENTATION = INDENTATION + " ";
    public static final String DIVIDER = "_".repeat(60);
    private static final String LOGO =
            " _               _          \n" + "    | |   _   _  ___| | ___   _ \n"
                    + "    | |  | | | |/ __| |/ / | | |      |\\__/,|   (`\\\n"
                    + "    | |__| |_| | (__|   <| |_| |    _.|o o  |_   ) )\n"
                    + "    |_____\\__,_|\\___|_|\\_\\\\__, |  -(((---(((--------\n"
                    + "                          |___/ ";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void printOutput(String... msg) {
        System.out.println(INDENTATION + DIVIDER);

        for (String string : msg) {
            System.out.println(SUBIDENTATION + string);
        }
        System.out.println(INDENTATION + DIVIDER + "\n");
    }

    public static void printList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : tasks) {
            sb.append(i + "." + task.toString() + "\n" + SUBIDENTATION);
            i++;
        }
        printOutput("Here are the tasks in your list:", sb.toString());
    }

    public String[] readCommand() {
        return this.sc.nextLine().trim().split(" ", 2);
    }

    public void printWelcomeMsg() {
        Ui.printOutput(LOGO, "Hello! I'm Lucky the cat", "What can I do for you?");
    }

    public void exit() {
        this.sc.close();
    }
}
