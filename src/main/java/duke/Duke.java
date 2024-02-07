package duke;

import command.Command;

import java.util.Scanner;

class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    Duke(String name, String fileFolder, String fileName) throws DukeException {
        ui = new Ui(name);
        storage = new Storage(fileFolder, fileName);
        taskList = new TaskList(storage.load());
    }

    void run() {
        boolean isDone = false;
        ui.greeting();
        while (!isDone) {
            try {
                String prompt = ui.readCommand();
                Command command = Parser.parse(prompt);
                command.execute(storage, taskList);
                ui.separatePrompt();
                isDone = command.isExit();
            } catch (DukeException e) {
                System.out.println(e);
                ui.separatePrompt();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Duke("Jerry", "./data", "duke.txt").run();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
