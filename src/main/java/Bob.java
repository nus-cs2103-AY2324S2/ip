public class Bob {
    private static void printFormatted(String[] lines) {
        String horizontalLine = "\t____________________________________________________________\n";
        StringBuilder formatted = new StringBuilder(horizontalLine);
        for (String line : lines) {
            formatted.append("\t ");
            formatted.append(line);
            formatted.append('\n');
        }
        formatted.append(horizontalLine);
        System.out.println(formatted.toString());
    }

    private static void printFormatted(String line) {
        printFormatted(new String[]{ line });
    }

    public static void main(String[] args) {
        printFormatted(new String[]{ "Hello! I'm Bob", "What can I do for you?" });
        printFormatted(new String[]{ "Bye. Hope to see you again soon!" });
    }
}
