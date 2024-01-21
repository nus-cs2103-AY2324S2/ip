public class PrintUtil {

    // Private constructor to prevent instantiation
    private PrintUtil() {}

    // Static utility method for printing lines
    public static void printSpacer() {
        indent();
        System.out.println("--------------------------------------------------------" +
                "----------");
    }

    public static void indent() {
        System.out.print("    ");
    }

    public static void print(String s) {
        printSpacer();
        indent();
        System.out.println(s.replaceAll("\n", "\n    "));
        printSpacer();
    }
}
