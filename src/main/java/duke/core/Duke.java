package duke.core;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e){
            ui.displayError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void echo() {

        while (ui.next()) {
            String msg = ui.getInput();
            Parser parser = new Parser(msg);

            try {
                if (msg.equalsIgnoreCase(Command.BYE.name())) {

                    ui.exit();
                    break;

                } else if (msg.equalsIgnoreCase(Command.LIST.name())) {

                    ui.formatReply(tasks.printList());

                } else if (msg.toUpperCase().startsWith(Command.MARK.name())){

                    int taskIndex = parser.parseMark();
                    ui.formatReply(tasks.markTask(taskIndex));
                    tasks.saveToStorage(storage);

                } else if (msg.toUpperCase().startsWith(Command.UNMARK.name())){

                    int taskIndex = parser.parseUnMark();
                    ui.formatReply(tasks.unmarkTask(taskIndex));
                    tasks.saveToStorage(storage);

                } else if (msg.toUpperCase().startsWith(Command.DELETE.name())) {

                    int taskIndex = parser.parseDelete();
                    ui.formatReply(tasks.deleteTask(taskIndex));
                    tasks.saveToStorage(storage);

                } else {

                    Task t = parser.parseAdd();
                    ui.formatReply(tasks.addTask(t));
                    tasks.saveToStorage(storage);

                }
            } catch (DukeException e) {

                ui.displayError(e.getMessage());

            }
        }

        ui.closeScanner();

    }

    public void run() {
        ui.greetUser();
        this.echo();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
