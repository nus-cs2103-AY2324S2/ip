package Panna;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


// to add: tag for A-Classes
// to add: tag for A-Collections
public class Panna {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static Parser parser;

    private static String filePath;
    private static String command = "";

    public Panna(String filePath) {
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

        Panna.command = s.nextLine();

        while (!Panna.command.equals("bye")) {

            if (Panna.command.equals("list")) {
                this.ui.listMessage(this.taskList);
            }


            else if (Panna.command.equals("mark")) {
                this.ui.mark(this.taskList);
            }

            else if (Panna.command.equals("unmark")) {
                this.ui.unmark(this.taskList);
            }

            else if (Panna.command.equals("event")) {
                this.ui.eventMessage(this.taskList, this.parser);
            }

            else if (Panna.command.equals("todo")) {
                this.ui.todoMessage(this.taskList);

            }

            else if (Panna.command.equals("deadline")) {
                this.ui.deadlineMessage(this.taskList, this.parser);

            }
            else if (Panna.command.equals("delete")) {
                this.ui.deleteMessage(this.taskList);
            }

            this.storage.write(filePath, this.taskList);




            Panna.command = s.nextLine();
        }


        this.ui.farewellMessage();

    }


    public static void main(String[] args) throws PannaException {
        Panna bot = new Panna("./panna.txt");
        bot.run();
    }
}
