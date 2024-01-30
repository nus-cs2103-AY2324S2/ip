package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Duke {
    Storage storage;
    Ui ui;
    TaskList taskList;
    Parser parser = new Parser();

    String command = "";
    String secondaryInput = "";
    Scanner scanner1;
    boolean isEnded = false;

    String[] commandList = new String[] {"bye", "mark", "unmark", "todo", "deadline", "event", "list"};
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    public void exit() {
        this.ui.bye();
        this.scanner1.close();
        this.storage.saveFile(this.taskList);
        this.isEnded = true;
        horizontalLines();
    }

    public void horizontalLines() {
        System.out.println("\n    ____________________________________________________________");
    }

    public void parse() {
        String commandInput = scanner1.nextLine();
        this.parser.input(commandInput, this, this.taskList);
    }

    public void run() {
        try {
            this.taskList = storage.loadFile();
        } catch (IOException e) {
            System.out.println("Run failed.");
        }
    }

    public static void main(String[] args) {
        Duke Duke1 = new Duke("data/tasks.txt");
        Duke1.run();
        Duke1.scanner1 = new Scanner(System.in);
        Duke1.horizontalLines();
        Duke1.ui.greeting();

        while (!Duke1.isEnded) {
            Duke1.horizontalLines();
            Duke1.parse();
            //Duke1.horizontalLines();
        }
    }
}
