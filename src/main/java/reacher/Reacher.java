package reacher;

import reacher.command.Command;

import java.time.format.DateTimeParseException;

public class Reacher {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Reacher(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadList());
    }

    public String getResponse(String text) {
        return "reacher says " + text;
    }

    /**
     * Prints welcome message and takes in user input, executes it until user exits.
     */
    public void run() {
        ui.printWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                ui.print("Enter a command:");
                String input = ui.readString();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ReacherException e) {
                ui.print(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.print("Pls provide correct format: yyyy-mm-dd");
            }
        }
    }
}
