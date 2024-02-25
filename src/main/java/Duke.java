import java.util.NoSuchElementException;

import command.Command;

import common.DukeException;
import common.Parser;
import common.Storage;
import common.Ui;

import task.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.loadData());

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Ui.showLine();

                Command cmd = new Parser(userInput, tasks).parse();
                cmd.execute();
                isExit = cmd.isExit();

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid task number. :(");
    
            } catch (NumberFormatException e) {
                System.out.println("Input is not an integer. :(");
    
            } catch (NoSuchElementException e) {
                System.out.println("Missing task number. :(");

            } catch (DukeException e) {
                System.out.println(e.getMessage());

            } finally {
                Ui.showLine();
            }
        }
        storage.saveDataAndExit(tasks);
        Ui.showLine();
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
