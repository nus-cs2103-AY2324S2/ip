public class PrintUtil {

    // Private constructor to prevent instantiation
    private PrintUtil() {}

    // Static utility method for printing lines
    private static void printSpacer() {
        indent();
        System.out.println("--------------------------------------------------------" +
                "----------");
    }

    private static void indent() {
        System.out.print("    ");
    }

    private static void taskIndent() {
        indent();
        System.out.print("  ");
    }

    public static void print(String s) {
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
