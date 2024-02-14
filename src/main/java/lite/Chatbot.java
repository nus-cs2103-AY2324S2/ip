package lite;
import java.io.IOException;
import java.util.Scanner;

import lite.task.TaskList;
import lite.util.LiteException;
import lite.util.Printer;


public class Chatbot {
    private Scanner scanner = new Scanner(System.in);
    private TaskList tasks;
    private Boolean isBye;


    public Chatbot() {
        this.tasks = new TaskList();
        this.isBye = false;
    }

    /**
     * Starts the chatbot
     */
    public void start() {
        try {
            this.tasks = new TaskList(Storage.load());
            greetings();
            while (!this.isBye) {
                Ui ui = new Ui(scanner.nextLine());
                this.isBye = ui.executeCommand(tasks);
            }
            tasks.saveFile();
            exit();
        } catch (IOException e) {
            LiteException.loadFileException();
        }
    }

    /**
     * Outputs a greeting message
     */
    private void greetings() {
        Printer.printHorizontalLine();
        String msg = " Hello! I'm LITE\n" +
                " What can I do for you?" ;
        System.out.println(msg);
        Printer.printHorizontalLine();
    }

    /**
     * Terminates the program
     */
    private void exit() {
        Printer.printHorizontalLine();
        String msg = " Bye. Hope to see you again soon!";
        System.out.println(msg);
        Printer.printHorizontalLine();
    }


}
