package BadApple.main;

import BadApple.task.Command;
import BadApple.task.Parser;
import BadApple.task.TaskList;
import BadApple.uiElements.DialogBox;

import BadApple.uiElements.Ui;
import javafx.scene.image.Image;

import java.io.*;

import static BadApple.task.Storage.loadSave;

/**
 * BadApple Class is the entry point to the
 * Task Creation, Storage and manipulation features.
 * note: PingGuo means Apple.
 */
public class BadPingGuo {
    public static final String FILENAME = "../data/whiteSpace.txt";

    private boolean isMakingFile = false;
    private final Image Mari = new Image(this.getClass().getResourceAsStream("/images/MARI.png"));

    /**
     * Shows the user their tasks to do when the app is first booted up
     * @return the initial TaskList when booting up
     */
    public DialogBox getInitialList() {
        try {
            File file = new File("../data/whiteSpace.txt");
            FileReader fr = new FileReader(file);

            String tasks = TaskList.listTasks(new BufferedReader(fr));
            loadSave(file);
            return DialogBox.getDukeDialog(tasks, Mari);

        } catch (FileNotFoundException f) {
            return askToMakeFile();
        } catch (IOException i) {
            return DialogBox.getDukeDialog("failed to read", Mari);
        }
    }

    public DialogBox askToMakeFile() {
        isMakingFile = true;
        return DialogBox.getDukeDialog(Ui.noWhiteSpaceFile(), Mari);
    }

    /**
     * Attempt to make files and inform the user (Sunny) of its status
     * @return an indication of whether the file creation was successful
     */
    public static String makeFile() {
        try {
            File f1 = new File("../data");
            File f = new File("../data/whiteSpace.txt");
            if (f1.mkdir() && f.createNewFile()) return "Welcome to headspace!";
        } catch (IOException e) {
            return "Humphrey has denied your entrance to white space! \n " +
                    "perhaps the write permissions aren't working?";
        }
        return "There is nothing here. Files may not be created.";
    }

    /**
     * An entry point for the response Duke (Mari) gives
     * @param input the text the user types into the text box
     * @return the Response for javaFX Mari to respond with
     */
    protected String getResponse(String input){
        String reply;
        if (input.equals("yes") && isMakingFile) {
            isMakingFile = false;
            return makeFile();
        } else if (isMakingFile) {
            isMakingFile = false;
            return "you may continue but nothing remains when you leave headspace";
        }
        assert (!isMakingFile);
        try {
            Command c = Parser.ProcessQuery(input);
            reply = c.execute();
        } catch (IOException e) {
            return "FAILED";
        } catch (BadAppleException b) {
            return b.toString();
        }
        return reply;
    }

}
