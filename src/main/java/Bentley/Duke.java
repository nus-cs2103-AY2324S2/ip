package Bentley;

import java.util.Scanner;
/**
 * The main class representing the Duke application.
 * Duke is a simple task management application that allows users to manage their tasks through a command-line interface.
 */
public class Duke {

    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    public static void main(String[] args) {
        storage = new Storage("data/duke.txt");
        taskList = new TaskList();
        ui = new Ui();

        // Load existing tasks from file
        storage.loadTasks(taskList.getTasks());

        ui.showWelcomeMessage();
        
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                String userInput = scanner.nextLine();

                if (userInput.equals("Bye")) {
                    ui.showByeMessage();
                    storage.writeTasks(taskList.getTasks());
                    break;
                } else {
                    Parser.parseCommand(userInput, taskList, ui, storage);
                    storage.writeTasks(taskList.getTasks());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
