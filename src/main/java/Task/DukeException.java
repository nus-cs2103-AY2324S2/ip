package task;

public class DukeException extends Exception {
    private String botMessage;

    public DukeException(String message, String botMessage) {
        super(message);
        this.botMessage = botMessage;
    }

    public String getBotMessage() {
        return botMessage;
    }
}
