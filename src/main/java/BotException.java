public class BotException extends Exception {
    public BotException(String errMessage) {
        super(TerminalUI.wrapWithSepLine("Error: " + errMessage));
    }
}
