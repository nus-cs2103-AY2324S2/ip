package chillchief;

import util.Parser;
import util.Storage;
import util.TaskList;
import util.TextUi;

import java.util.Scanner;

import java.io.IOException;
import exceptions.ChillChiefException;

public class ChillChief {

    private Storage storage;
    private TaskList taskList;
    private TextUi ui;

    public ChillChief(String filePath) throws IOException {
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
            } catch (ChillChiefException e) {
                System.out.println(ui.showErrorMessage("Error!"));
            } catch (Exception e) {
                System.out.println("An unexpected error occurred.");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChillChief("./data/chillchief.txt").run();
    }
}
