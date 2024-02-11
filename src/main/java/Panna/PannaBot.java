package Panna;

import java.util.Scanner;


// to add: tag for A-Classes
// to add: tag for A-Collections
public class PannaBot {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static Parser parser;

    private static String filePath;
    private static String command = "";

    public PannaBot(String filePath) {
        this.filePath = filePath;
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser("yyyy-MM-dd");
        this.taskList = new TaskList();

    }


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

            else if (PannaBot.command.equals("find")) {
                this.ui.find(this.taskList);
            }

            this.storage.write(filePath, this.taskList);




            PannaBot.command = s.nextLine();
        }


        this.ui.farewellMessage();

    }


    public static void main(String[] args) throws PannaException {
        PannaBot bot = new PannaBot("./panna.txt");
        bot.run();
    }
}
