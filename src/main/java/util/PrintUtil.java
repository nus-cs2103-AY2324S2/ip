package util;

import task.Task;

import java.util.List;

public class PrintUtil {

    // Private constructor to prevent instantiation
    private PrintUtil() {

    }

    // Static utility method for printing lines
    private static void printSpacer() {
        System.out.println();
        indent();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"
                + " - - - -\n");
    }

    private static void indent() {
        System.out.print("    ");
    }

    private static void taskIndent() {
        indent();
        System.out.print("  ");
    }

    public static void print(String s) {
        System.out.println();
        printSpacer();
        indent();
        System.out.println(s.replaceAll("\n", "\n    "));
        printSpacer();
    }

    public static void print(Task t) {
        taskIndent();
        System.out.println(t);
        printSpacer();
    }

    public static void printList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        if (!tasks.isEmpty()) {
            sb.append("I found these tasks!\n    ");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(".").append(tasks.get(i).toString());
                if (i != tasks.size() - 1) {
                    sb.append("\n    ");
                }
            }
        } else {
            sb.append("You don't have any tasks which match that description... (◞‸◟；)");
        }
        print(sb.toString());
    }
}
