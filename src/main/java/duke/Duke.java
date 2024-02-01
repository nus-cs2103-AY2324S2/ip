package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser = new Parser();
    private Scanner scanner;
    private boolean isEnded = false;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    public void exit() {
        this.ui.bye();
        this.scanner.close();
        this.storage.saveFile(this.taskList);
        this.isEnded = true;
        horizontalLines();
    }

    public void horizontalLines() {
        System.out.println("\n    ____________________________________________________________");
    }

    public void parse() {
        String commandInput = scanner.nextLine();
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
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
        duke.scanner = new Scanner(System.in);
        duke.horizontalLines();
        duke.ui.greeting();

        while (!duke.isEnded) {
            duke.horizontalLines();
            duke.parse();
        }
    }
}
