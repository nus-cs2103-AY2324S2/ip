package ciara;

import java.io.File;

import ciara.storage.TaskList;
import ciara.ui.Gui;
import ciara.ui.Ui;

/**
 * The Ciara class provides the Ciara chatbot application
 *
 * @author Ryan NgWH
 */
public class Ciara {
    /**
     * File to save storage to
     */
    private static File saveFile;

    /**
     * Task list for the Ciara instance
     */
    private TaskList taskList;

    /**
     * UI of the Ciara instance
     */
    private Ui ui;

    /**
     * Creates a Ciara instance
     *
     * @param file File to save and load tasks
     */
    public Ciara(File file) {
        this.ui = new Gui();
        Ciara.saveFile = file;

        // Create data directory (if required)
        file.getParentFile().mkdirs();

        this.taskList = new TaskList(Ciara.saveFile);
    }

    /**
     * Gets the save file of the application
     *
     * @return Save file of the application
     */
    public static File getSaveFile() {
        return Ciara.saveFile;
    }

    /**
     * Sets the save file of the application
     *
     * @param file File to use as the save file of the application
     */
    public static void setSaveFile(File file) {
        Ciara.saveFile = file;
    }

    /**
     * Runs the Ciara instance
     *
     * @param args Arguments to pass to the application
     */
    public void run(String[] args) {
        assert this.ui != null; // UI should exist
        assert this.taskList != null; // Tasklist should exist

        this.ui.startUI(this.taskList, args);
    }

    public static void main(String[] args) {
        new Ciara(new File("data/tasks.json")).run(args);
    }
}
