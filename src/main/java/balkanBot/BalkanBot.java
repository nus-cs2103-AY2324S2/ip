/**
 * This is the main method for the Balkan Bot Chat Bot.
 * The Balkan Bot is able to make and store various tasks such as ToDos, Deadlines and Events.
 * It is also able to Mark or Unmark them as complete.
 */

package balkanBot;

import GUI.GUIUi;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import parser.Parser;

import java.util.Scanner;

public class BalkanBot {
    private final Storage storage;
    private TaskList tasks;
    private final GUIUi guiUi;

    private String forthegrade;

    /**
     * Initialises Balkan Bot
     *
     * @param filePath The file path of a text file containing previously stored tasks
     */

    public BalkanBot(String filePath) {
        guiUi = new GUIUi();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BalkanBotException e) {
            System.out.println(guiUi.showLoadingError());
            tasks = new TaskList();
        }
    }

    public BalkanBot() {
        guiUi = new GUIUi();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (BalkanBotException e) {
            System.out.println(guiUi.showLoadingError());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the bot
     */
//    public void run() {
//        boolean isExit = false;
//        while (!isExit) {
//            isExit = Parser.parse(command, tasks, ui);
//        }
//        storage.save(tasks);
//    }

//    public static void main(String[] args) {
//        new BalkanBot("balkanbot.txt").run();
//    }

    public String getResponse(String input) {
        return Parser.parse(input, tasks, guiUi, storage);
    }
}