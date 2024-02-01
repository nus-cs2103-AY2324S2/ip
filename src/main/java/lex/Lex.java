package lex;

import java.util.Scanner;

import lex.parser.Parser;
import lex.parser.command.Command;
import lex.storage.Storage;
import lex.tasks.TaskList;
import lex.ui.Ui;

public class Lex {
    final private Parser parser;
    final private Ui ui;

    public Lex(String filePath) {
        ui = new Ui(new Scanner(System.in));
        Storage storage = new Storage(filePath);

        TaskList tasks;
        try {
            tasks = storage.load();
        } catch (Exception e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }

        parser = new Parser(tasks, ui, storage);
    }

    public void run() {
        ui.welcome();

        while (true) {
            try {
                String input = ui.read();
                Command command = parser.parse(input);

                if (command.execute()) {
                    break;
                }
            } catch (Exception e) {
                ui.print(e.getMessage());
            }
        }

        ui.dispose();
    }

    public static void main(String[] args) {
        new Lex("./data.json").run();
    }
}
