import java.util.List;

public class TerminalUI {
    private static final String SEPARATOR_LINE = "_____________________________________________________________";

    public static String wrapWithSepLine(String input) {
        return SEPARATOR_LINE + "\n" + input + "\n" + SEPARATOR_LINE;
    }

    public static void printSepLine() {
        System.out.println(SEPARATOR_LINE);
    }

    // Overloaded method to print list of strings
    public static String wrapWithSepLine(List<String> list) {
        StringBuilder sb = new StringBuilder(SEPARATOR_LINE);
        for (String item : list) {
            sb.append("\n").append(item);
        }
        sb.append("\n").append(SEPARATOR_LINE);
        return sb.toString();
    }
}