/**
 * This is the main method for the Balkan Bot Chat Bot.
 * The Balkan Bot is able to make and store various tasks such as ToDos, Deadlines and Events.
 * It is also able to Mark or Unmark them as complete.
 */

package balkan;

import gui.GuiUi;
import storage.Storage;
import task.TaskList;
import parser.Parser;

public class BalkanBot {
    private final Storage storage;
    private TaskList tasks;
    private final GuiUi guiUi;

    /**
     * Initialises Balkan Bot
     */

    public BalkanBot() {
        guiUi = new GuiUi();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (BalkanBotException e) {
            System.out.println(guiUi.showLoadingError());
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        return Parser.parse(input, tasks, guiUi, storage);
    }
}