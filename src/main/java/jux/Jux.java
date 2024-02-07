package jux;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Jux {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Jux(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);

        if (!tasks.isEmpty()) {
            ui.printList(tasks.getTaskList());
        }
        ui.showWelcome();
            while(true) {
            try {
                String input = ui.takeInput();
                if (Parser.isExit(input)) {
                    ui.showGoodbye();
                    break;
                }
                Parser.parsingInput(input, tasks, ui);
                storage.saveFile(tasks.getTaskList());
                } catch (JuxException e) {
                System.err.println(e.getMessage());
            }


        }



    }

    public static void main(String[] args) {
        new Jux("data/Jux.txt").run();
    }
}

