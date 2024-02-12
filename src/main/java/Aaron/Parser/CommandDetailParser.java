package aaron.parser;

public class CommandDetailParser {

    public static String getCommandDetails(String userInput) {
        String[] tokenizedUserCommandWords = userInput.split(" ", 2);
        if (tokenizedUserCommandWords.length < 2) {
            return null;
        } else {
            return tokenizedUserCommandWords[1];
        }
    }

}
