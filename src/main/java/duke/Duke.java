package duke;

import command.Command;
import exception.UnknownCommandException;
import task.TaskList;


public class Duke {
    private static final String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Ui ui = new Ui();
    private TaskList taskList = new TaskList();
    private Storage storage;

    Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage();
        try {
            this.storage.loadData(this.taskList);
        } catch (Exception e) {
            ui.showErrorMessage(e);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        ui.sayGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parseCommand(userInput);
                command.execute(duke.taskList, ui);
                isExit = command.isExit();
            } catch (IllegalArgumentException e) {
                if (e.getMessage().contains("No enum constant Duke.Command.")) {
                    ui.showErrorMessage(new UnknownCommandException());
                } else {
                    ui.showErrorMessage(e);
                }
            } catch (Exception e) {
                ui.showErrorMessage(e);
            }
        }
        try {
            duke.storage.saveData(duke.taskList);
        } catch (Exception e) {
            ui.showErrorMessage(e);
        }
    }
}
