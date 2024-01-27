package util;

import task.Task;

public class PrintUtil {

    // Private constructor to prevent instantiation
    public PrintUtil() {

    }

    // Static utility method for printing lines
    private static void printSpacer() {
        System.out.println();
        indent();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" +
                " - - - -\n");
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
}
