package chillchief;

import exceptions.ChillChiefException;
import util.Parser;
import util.Storage;
import util.TaskList;
import util.TextUi;

import java.io.IOException;
import java.util.Scanner;

/**
 * ChillChief is a task management application.
 * Users can add, mark, unmark, delete, find, various tasks such as todos, deadlines and events.
 */
public class ChillChief {

    private Storage storage;
    private TaskList taskList;
    private TextUi ui;

    /**
     * Constructs an instance of the ChillChief application.
     * Initializes the TextUi, Storage and TaskList components.
     * Displays an error is initialization of components fails.
     *
     * @param filePath The file path where tasks are saved and loaded from.
     * @throws IOException If an error occurs in initialization of components.
     */
    public ChillChief(String filePath) throws IOException {
        ui = new TextUi();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Runs the ChillChief application.
     */
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
