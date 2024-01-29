import Commands.ByeCommand;
import Commands.Command;
import Exceptions.InvalidInputException;
import Storage.Storage;
import TaskList.TaskList;
import TaskList.Tasks.Deadline;
import TaskList.Tasks.Event;
import TaskList.Tasks.Task;
import TaskList.Tasks.ToDo;
import UI.IrisUI;
import Parser.Parser;

import java.io.*;
import java.util.ArrayList;

public class Iris {

    private IrisUI ui;
    private Storage storage;
    private TaskList taskList;
    public void start() {
        try {
            this.ui = new IrisUI();
            this.storage = new Storage();

            this.taskList = new TaskList();
            storage.load(this.taskList);
            ui.helloMessage();

        } catch (IOException e) {
            ui.showInitFailedMessage();
            throw new RuntimeException(e);
        }
    }

    /** Runs the program until termination.  */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            String result = executeCommand(command);
            ui.printWithDelimiter(result);

        } while (!ByeCommand.isExit(command));
    }
    private String executeCommand(Command command) {
        try {
            command.setData(this.taskList);
            String result = command.execute();
            this.storage.save(this.taskList);
            return result;
        } catch (Exception e) {
            ui.printWithDelimiter(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, InvalidInputException {
        Iris iris = new Iris();
        iris.run();
    }
}
