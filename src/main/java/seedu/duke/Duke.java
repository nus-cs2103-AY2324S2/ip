package seedu.duke;

import java.io.IOException;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
//            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.openingMessage();
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        while (!word.equals("bye")) {
            // command dependent logic
            if (word.equals("list")) {
                ui.printList(tasks.getList());
                word = sc.nextLine();
            } else if (word.startsWith("mark")) {
                Parser.parseMark(word, tasks, ui);
                word = sc.nextLine();
            } else if (word.startsWith("unmark")) {
                Parser.parseUnmark(word, tasks, ui);
                word = sc.nextLine();
            } else if (word.startsWith("deadline")) {
                Parser.parseDeadline(word, tasks, ui);
                word = sc.nextLine();
            } else if (word.startsWith("todo")) {
                Parser.parseTodo(word, tasks, ui);
                word = sc.nextLine();
            } else if (word.startsWith("event")) {
                Parser.parseEvent(word, tasks, ui);
                word = sc.nextLine();
            } else if (word.startsWith("delete")) {
                Parser.parseDelete(word, tasks, ui);
                word = sc.nextLine();
            } else {
                try {
                    throw new DukeException("Sorry, I didn't understand that.");
                } catch (DukeException d) {
                    ui.printError(d);
                    word = sc.nextLine();
                }
            }
        }
        storage.saveList(tasks.getList());
        ui.closingMessage();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }

}
