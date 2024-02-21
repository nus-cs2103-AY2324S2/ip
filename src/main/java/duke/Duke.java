package duke;

import java.util.NoSuchElementException;

public class Duke {
    Ui ui = new Ui();
    Parser parser = new Parser();
    TaskList tasks = new TaskList();
    Storage storage = new Storage();

    /**
     * Main function to run the chatbot.
     */
    public void run() {
        try {
            tasks.loadFromSavedData(storage.loadFromFile(), parser);
        } catch (DukeException e) {
            storage.deleteFile();
            ui.printCorruptedFileMessage(e);
        }
        ui.printBanner();
        while (true) {
            String prompt;
            try {
                prompt = ui.askForPrompt();
            } catch (NoSuchElementException e) {
                System.out.println();
                try {
                    new ByeCommand().execute(tasks);
                } catch (DukeException e2) {
                    System.out.println(e2.getMessage());
                }
                break;
            }

            Command command;
            try {
                command = parser.parse(prompt);
                command.execute(tasks);
                storage.saveToFile(tasks.getSaveData());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    public static void main(String[] args) {
        Commands.registerCommands();
        Duke duke = new Duke();
        duke.run();
    }
}
