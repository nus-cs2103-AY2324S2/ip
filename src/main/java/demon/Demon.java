package demon;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.format.DateTimeParseException;

/**
 * A chatbot that allows user to add, delete, and modify tasks or reminders.
 */
public class Demon {
    private TaskList tasks;
    private final Ui ui;
    String filePath = "./output/demon.txt";
    Command command = new Command(filePath);
    boolean isRun = true;

    /**
     * Initializes the bot and load tasks saved in taskList.txt file
     * if there is any previous tasks.
     */
    public Demon() {
        ui = new Ui();
        final Storage STORAGE = new Storage(this.filePath);
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (NoSuchFileException e) {
            tasks = new TaskList();
        } catch (IOException | NoSuchTaskException e) {
            throw new RuntimeException(e);
        }

    }

    public String getResponse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            isRun = false;
            return ui.exitMessage();
        } else {
            try {
                return command.callCommand(input, this.tasks);
            } catch (IOException | DateTimeParseException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                return Ui.outOfBoundsIndex(e);
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }
}


