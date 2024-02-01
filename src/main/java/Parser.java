public class Parser {
    public String parseCommand(String userInput) {
        return userInput.split(" ")[0].trim();
    }
}
