package duke.parser;

public class Parser {
    public static String[] parse(String userInput) {
        return userInput.trim().split(" ", 2);
    }
}


