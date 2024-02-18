package dude;

import java.util.NoSuchElementException;
import java.util.Scanner;

import dude.Commands.Command;
import dude.Commands.CommandTypes;
import dude.Commands.Parser;
import dude.Exceptions.DudeException;
import dude.Tasks.TaskList;
import dude.Utils.Storage;
import dude.Utils.Ui;


public class Dude {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    private boolean isRunning = true;

    public Dude(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        TaskList temp = null;
        try {
            temp = this.storage.loadTasks();
        } catch (Exception e) { //Thrown when file gets corrupted
            ui.showMessage("An error occurred while loading the tasks. Deleting the storage and starting with an empty task list.");
            this.storage.deleteStorage();
            temp = new TaskList();
        }

        this.taskList = temp;
    }

    public void run() {

        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (this.isRunning) {
            String input = extractInput(sc);
            Command command = Parser.parse(input, taskList);

            String response = executeCommand(command);
            ui.showMessage(response);

            saveToDisk();

            if (command.getCommandType() == CommandTypes.BYE) {
                this.isRunning = false;
            }
        }
    }

    private static String extractInput(Scanner sc) {
        String input = "";
        try {
            input = sc.nextLine();
        } catch (NoSuchElementException e) {
            //this will not be handled. App will only exit at bye command.
            input = "";
        }
        return input;
    }

    private static String executeCommand(Command command) {
        try {
            return command.execute();
        } catch (DudeException | IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    private void saveToDisk() {
        try {
            this.storage.saveTasks(taskList);
        } catch (Exception e) {
            this.ui.showMessage("An error occurred while saving the tasks.");
        }
    }

    public static void main(String[] args) {
        new Dude("data/tasklist.ser").run();
    }
}
