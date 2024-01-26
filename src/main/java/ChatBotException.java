public class ChatBotException extends Exception {
    public ChatBotException(String errorMessage) {
        super("____________________________________________________________" + "\n"
                + "\t" + errorMessage + "\n" + "____________________________________________________________");
        }
    }
