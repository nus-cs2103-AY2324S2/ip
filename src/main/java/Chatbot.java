import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {
    private Scanner scanner = new Scanner(System.in);
    private TaskList tasks;
    private Boolean isBye;


    public Chatbot() {
        this.tasks = new TaskList();
        this.isBye = false;
    }

    public void start() {
        try {
            this.tasks = new TaskList(SavedFile.load());
            greetings();
            while (!this.isBye) {
                Ui ui = new Ui(scanner.nextLine());
                this.isBye = ui.parse(tasks);
            }
            tasks.saveFile();
            exit();
        } catch (IOException e) {
            LiteException.LoadFileException();
        }
    }

    private void greetings() {
        Printer.printHorizontalLine();
        String msg = " Hello! I'm LITE\n" +
                " What can I do for you?" ;
        System.out.println(msg);
        Printer.printHorizontalLine();
    }

    private void exit() {
        Printer.printHorizontalLine();
        String msg = " Bye. Hope to see you again soon!";
        System.out.println(msg);
        Printer.printHorizontalLine();
    }


}
