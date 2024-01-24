import controller.TerminalController;
import service.FeedbackService;

public class Duke {
    public static void main(String[] args) {
        TerminalController controller = new TerminalController();

        controller.printWelcomeMessage();
        controller.listen();
    }
}
