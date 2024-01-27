import java.util.Scanner;

public class Bob {
    private static void printFormatted(String[] lines) {
        String horizontalLine = "    .-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        StringBuilder formatted = new StringBuilder(horizontalLine);
        for (String line : lines) {
            formatted.append("     ");
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
        printFormatted(new String[]{ "yo im bob", "what do you want" });

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            }
            printFormatted(command);
        }

        printFormatted("ok");
    }
}
