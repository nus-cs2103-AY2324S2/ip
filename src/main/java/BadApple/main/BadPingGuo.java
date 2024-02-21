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
    public static final String FILENAME = "src/main/data/whiteSpace.txt";

    private boolean isMakingFile = false;
    private final Image Mari = new Image(this.getClass().getResourceAsStream("/images/MARI.png"));

    public DialogBox getInitialList() {
        try {
            File file = new File("src/main/data/whiteSpace.txt");
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

    public static String makeFile() {
        try {
            File f1 = new File("src/main/data");
            File f = new File("src/main/data/whiteSpace.txt");
            if (f1.mkdir() && f.createNewFile()) return "Welcome to headspace!";
        } catch (IOException e) {
            return "Humphrey has denied your entrance to white space! \n " +
                    "perhaps the write permissions aren't working?";
        }
        return "There is nothing here. Files may not be created.";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input){
        String reply;
        if (input.equals("yes") && isMakingFile) {
            isMakingFile = false;
            return makeFile();
        }

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
