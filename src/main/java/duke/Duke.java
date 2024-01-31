package duke;//package duke;

import duke.task.*;
import duke.storage.*;
import duke.ui.*;
import duke.parser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath, String botName) {
        this.storage = new Storage(filePath);
        this.ui = new Ui(botName);
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();
        this.taskList = new TaskList(loadedTasks, this.ui);
        this.parser = new Parser(this.ui, this.storage, this.taskList);
    }

    public void run() throws IOException {
        ui.sendWelcome();
        boolean isBye = false;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            parser.execute(userInput);
            if (userInput.equals("bye")) {
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Duke("./data/duke.txt", "Hammy").run();
    }
}
