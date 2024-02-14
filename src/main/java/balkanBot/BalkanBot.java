package balkanBot;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import parser.Parser;

import java.util.Scanner;

public class BalkanBot {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public BalkanBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BalkanBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        Scanner input = new Scanner(System.in);
        while (!isExit) {
            String command = input.nextLine();
            isExit = Parser.parse(command, tasks, ui);
        }
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new BalkanBot("balkanbot.txt").run();
    }
}