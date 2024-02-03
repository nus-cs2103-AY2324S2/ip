public class Parser {
    public static CommandEnum parseCommand(String input) {
        try {
            return CommandEnum.valueOf(input.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandEnum.UNKNOWN;
        }
    }

    public static String parseArgs(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length > 1) {
            return splitInput[1];
        } else {
            return "";
        }
    }
}
