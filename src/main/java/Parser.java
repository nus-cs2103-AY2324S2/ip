import java.util.Scanner;

public class Parser {
    public static String[] parse (Scanner scanner) {
        // Separating the command and description from user input
        String[] commandDesc = scanner.nextLine().split("\\s+", 2);
        String command = commandDesc[0];
        String desc = "";

        if (commandDesc.length > 1) {
            desc = commandDesc[1];
        }

        return new String[]{ command, desc };
    }

    public static void checkDescription(String desc) throws LaiException {
        if (desc.equals("")) {
            throw new LaiException("Dude. What am I supposed to do with an empty description?");
        }
    }

}
