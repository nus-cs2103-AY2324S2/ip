package duke;

import java.io.IOException;

/**
 * This Duke program is a chatbot that takes handles a list for the user.
 */

public class Duke {

    private UI ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;
    public Duke() throws IOException {
        ui = new UI();
        storage = new Storage();
        parser = new Parser();

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (NullPointerException e) {
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {

        ui.startMsg();

        boolean finish = false;

        while (!finish) {
            String text = ui.read();
            String cmd = parser.parseCmd(text);

            try {
                if (cmd.equals("bye")) {
                    finish = true;
                } else if (cmd.equals("list")) {
                    ui.printList(tasks.get());
                } else if (cmd.equals("mark") || cmd.equals("unmark")) {
                    tasks.marked(parser.parseMark(text));
                    storage.saveTasks(tasks.get());
                } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")){
                    tasks.addItem(parser.parseAdd(text));
                    storage.saveTasks(tasks.get());
                } else if (cmd.equals("delete")) {
                    tasks.deleteItem(parser.parseDelete(text));
                    storage.saveTasks(tasks.get());
                } else if (cmd.equals("find")) {
                    tasks.findItem(parser.parseFind(text));
                } else {
                    throw new DukeException("Your input is invalid!");
                }
            } catch (DukeException de) {
                ui.showError(de);
            }
        }

        ui.byeMsg();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}

