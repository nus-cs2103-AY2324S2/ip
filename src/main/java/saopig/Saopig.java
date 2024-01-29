package saopig;

import saopig.command.Command;
import saopig.task.TaskList;

/**
 * Represents a Saopig chat bot.
 * A <code>Saopig</code> object corresponds to a chat bot that can
 * help users to manage their tasks.
 */
public class Saopig {
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();
    private Storage storage = new Storage(this.ui, this.taskList);

    /**
     * Runs the Saopig chatbot.
     */
    public static void main(String[] args) {
        new Saopig().run();
    }

    /**
     * Runs the Saopig chatbot until the user exits.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
                ui.printLine();
            } catch (SaopigInvaildSizeException e) {
                ui.showError("Unknown command error.");
                ui.printLine();
            }
        }
        ui.showGoodbye();
    }
}
