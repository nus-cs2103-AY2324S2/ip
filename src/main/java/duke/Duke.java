package duke;

import command.Command;

import java.util.Scanner;

/**
 * Represents the whole program that handles the user interaction, user prompt parsing, task list and file management,
 * and executions on the tasks.
 */
class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructs a new Duke Program.
     *
     * @param name       The name given by the user to the bot program
     * @param fileFolder The location where the file of tasks needs to be stored
     * @param fileName   The name of the file storing all tasks
     * @throws DukeException if initialization is unsuccessful
     */
    Duke(String name, String fileFolder, String fileName) throws DukeException {
        ui = new Ui(name);
        storage = new Storage(fileFolder, fileName);
        taskList = new TaskList(storage.load());
    }

    /**
     * Runs the whole cycle of the program.
     * Greets the user, accepts prompts, and ends the execution under given prompts.
     */
    void run() {
        boolean isDone = false;
        ui.greeting();
        while (!isDone) {
            try {
                String prompt = ui.readCommand();
                Command command = Parser.parse(prompt);
                command.execute(storage, taskList);
                ui.separatePrompt();
                isDone = command.isExit();
            } catch (DukeException e) {
                System.out.println(e);
                ui.separatePrompt();
            }
        }
    }

    /**
     * Initializes the bot program and runs it.
     * User can assign the name, file folder, and name of the file.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            new Duke("Jerry", "./data", "duke.txt").run();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
