public class ChatBotException extends Exception {
    public ChatBotException(String errorMessage) {
        super("\t" + errorMessage + "\n" + "____________________________________________________________");
        }
    }
