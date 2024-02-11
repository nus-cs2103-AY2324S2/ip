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
        storage.read(filePath, taskList);
        ui.launchMessage();
        Scanner s = new Scanner(System.in);

        PannaBot.command = s.nextLine();

        while (!PannaBot.command.equals("bye")) {

            if (PannaBot.command.equals("list")) {
                ui.listMessage(taskList);
            }


            else if (PannaBot.command.equals("mark")) {
                ui.mark(taskList);
            }

            else if (PannaBot.command.equals("unmark")) {
                ui.unmark(taskList);
            }

            else if (PannaBot.command.equals("event")) {
                ui.eventMessage(taskList, parser);
            }

            else if (PannaBot.command.equals("todo")) {
                ui.todoMessage(taskList);

            }

            else if (PannaBot.command.equals("deadline")) {
                ui.deadlineMessage(taskList, parser);

            }
            else if (PannaBot.command.equals("delete")) {
                ui.deleteMessage(taskList);
            }

            storage.write(filePath, taskList);




            PannaBot.command = s.nextLine();
        }


        ui.farewellMessage();

    }


    public static void main(String[] args) throws PannaException {
        PannaBot bot = new PannaBot("./panna.txt");
        bot.run();
    }
}
