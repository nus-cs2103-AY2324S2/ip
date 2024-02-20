package panna;

import java.util.Scanner;


// to add: tag for A-Classes
// to add: tag for A-Collections

/**
 * PannaBot is the main interface which runs the bot.
 * It comprises of many features of a chatbot, including list, mark, unmark among others!
 */
public class PannaBot {
    private static Parser parser;
    private static String filePath;
    private static String command = "";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    /**
     * Constructor method
     * @param filePath
     */
    public PannaBot(String filePath) {
        this.filePath = filePath;
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser("yyyy-MM-dd");
        this.taskList = new TaskList();

    }


    /**
     * The run method begins the launch of the bot by accumulating all the methods from the
     * various files and running them!
     * @throws PannaException
     */

    public void run() throws PannaException {
        storage.read(filePath, taskList);
        ui.launchMessage();
        Scanner s = new Scanner(System.in);

        PannaBot.command = s.nextLine();

        while (!PannaBot.command.equals("bye")) {

            if (PannaBot.command.equals("list")) {
                ui.listMessage(taskList);
            } else if (PannaBot.command.equals("mark")) {
                ui.mark(taskList);
            } else if (PannaBot.command.equals("unmark")) {
                ui.unmark(taskList);
            } else if (PannaBot.command.equals("event")) {
                ui.eventMessage(taskList, parser);
            } else if (PannaBot.command.equals("todo")) {
                ui.todoMessage(taskList);
            } else if (PannaBot.command.equals("deadline")) {
                ui.deadlineMessage(taskList, parser);
            } else if (PannaBot.command.equals("delete")) {
                ui.deleteMessage(taskList);
            } else if (PannaBot.command.equals("find")) {
                ui.find(this.taskList);
            }
            storage.write(filePath, taskList);
            PannaBot.command = s.nextLine();
        }

        ui.farewellMessage();

    }


    /**
     * Runs the function each time main is called.
     * @param args
     * @throws PannaException
     */
    public static void main(String[] args) throws PannaException {
        PannaBot bot = new PannaBot("./panna.txt");
        bot.run();
    }
}
