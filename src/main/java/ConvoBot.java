import commands.Command;
import exceptions.ConvoBotException;
import utils.*;

public class ConvoBot {
    private final TaskList tasks;
    private final UI ui;

    public ConvoBot(String filePath) {
        tasks = new TaskList(new Storage(filePath));
        ui = new UI();
    }

    public void run() {
        ui.showWelcomeMsg();
        while (true) {
            String userInput = ui.readUserInput();
            Command c;
            try {
                c = Parser.parseUserInput(userInput);
                if (c.isExit()) {
                    break;
                }
                ui.showHorizontalLine(false);
            } catch (ConvoBotException e) {
                ui.showHorizontalLine(false);
                ui.showError(e.getMessage());
                ui.showHorizontalLine(true);
                continue;
            }
            try {
                c.execute(tasks, ui);
            } catch (ConvoBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showHorizontalLine(true);
            }
        }
        ui.showExitMsg();
    }

    public static void main(String[] args) {
        new ConvoBot("./data/tasks.txt").run();
    }
}
