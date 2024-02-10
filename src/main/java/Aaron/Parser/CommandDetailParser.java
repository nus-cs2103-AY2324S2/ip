package Aaron.Parser;
public class CommandDetailParser {

    public static String getCommandDetails(String userInput) {
        String[] tokenizedUserCommandWords = userInput.split(" ", 2);
        if (tokenizedUserCommandWords.length < 2) {
            return null;
        } else {
            return tokenizedUserCommandWords[1];
        }
    }

    public static void main(String[] args) {
        System.out.println(getCommandDetails("add todo swim"));
    }

}
