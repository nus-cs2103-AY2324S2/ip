package Panna;

import java.util.Scanner;


// to add: tag for A-Classes
// to add: tag for A-Collections

/**
 * PannaBot is the main interface which runs the bot.
 * It comprises of many features of a chatbot, including list, mark, unmark among others!
 */
public class PannaBot {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static Parser parser;

    private static String filePath;
    private static String command = "";

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
        this.storage.read(filePath, this.taskList);
        this.ui.launchMessage();
        Scanner s = new Scanner(System.in);

        PannaBot.command = s.nextLine();

        while (!PannaBot.command.equals("bye")) {

            if (PannaBot.command.equals("list")) {
                this.ui.listMessage(this.taskList);
            }


            else if (PannaBot.command.equals("mark")) {
                this.ui.mark(this.taskList);
            }

            else if (PannaBot.command.equals("unmark")) {
                this.ui.unmark(this.taskList);
            }

            else if (PannaBot.command.equals("event")) {
                this.ui.eventMessage(this.taskList, this.parser);
            }

            else if (PannaBot.command.equals("todo")) {
                this.ui.todoMessage(this.taskList);

            }

            else if (PannaBot.command.equals("deadline")) {
                this.ui.deadlineMessage(this.taskList, this.parser);

            }
            else if (PannaBot.command.equals("delete")) {
                this.ui.deleteMessage(this.taskList);
            }

            this.storage.write(filePath, this.taskList);




            PannaBot.command = s.nextLine();
        }


        this.ui.farewellMessage();

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
