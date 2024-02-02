package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * Main Class for our Chat bot
 */
public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/husserl.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/satre.jpg"));

    /**
     * Constructor for chatbot.
     * @param filePath file to load.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            String[] pathStep = filePath.split("/");
            String progressivePath = "";
            for (int i = 0; i < pathStep.length - 1; i++) {
                String dir = pathStep[i];
                progressivePath = String.format("%s%s/", progressivePath, dir);
            }
            File directory = new File(progressivePath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File makeupFile = new File(filePath);
            try {
                makeupFile.createNewFile();
            } catch (IOException ex) {
                System.out.println("Logically it won't happen, but who knows?");
                System.exit(-1);
            }
            tasks = new TaskList(new ArrayList<>());
        }
        parser = new Parser(tasks);
    }

    String getResponse(String input) {
        try {
            String fullCommand = input;
            Command cmd = parser.parse(fullCommand);
            String tmp = cmd.reply();
            storage.writeToFile(tasks.getTaskList());
            return tmp;
        } catch (DukeException | IOException e) {
            return String.format("    %s\n", e.getMessage());
        }
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}
