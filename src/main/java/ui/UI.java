package ui;

import tasklist.TaskList;

import java.util.Scanner;

public class UI {
    public static void print(Object text) {
        System.out.println("\t" + text.toString());
    }

    public static String nextLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().strip();
    }

    public static void printTasks(TaskList tasks) {
        UI.print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            String output = (i + 1) + "." + tasks.getTask(i);
            UI.print(output);
        }
    }

}
