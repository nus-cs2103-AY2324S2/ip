package chillchief;

import exceptions.DukeException;
import util.Parser;
import util.Storage;
import util.TaskList;
import util.TextUi;

import java.io.IOException;
import java.util.Scanner;

public class ChillChief {

    private Storage storage;
    private TaskList taskList;
    private TextUi ui;

    public ChillChief(String filePath) throws DukeException, IOException {
        ui = new TextUi();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public void run() {
        System.out.println(this.ui.showIntroMessage());
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            try {
                String userInput = scanner.nextLine();
                String response = Parser.parseInput(userInput, taskList, ui, storage);
                System.out.println(response);
                isExit = userInput.trim().equalsIgnoreCase("bye");
                storage.save(taskList.getAllTasks());
            } catch (DukeException e) {
                System.out.println(ui.showErrorMessage("Error!"));
            } catch (Exception e) {
                System.out.println("An unexpected error occurred.");
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new ChillChief("./data/chillchief.txt").run();
    }
}
