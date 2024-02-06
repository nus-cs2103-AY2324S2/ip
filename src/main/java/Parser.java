public class Parser {

    public String extractCommand(String string) throws InvalidCommandException {
        String[] splitString;
        try {
            splitString = string.split(" ", 2);
            return splitString[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(string);
        }

    }

    public String extractDescription(String string) throws IncompleteCommandException {
        String[] splitString;
        try {
            splitString = string.split(" ", 2);
            return splitString[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncompleteCommandException("Command is missing a description.");
        }

    }

}