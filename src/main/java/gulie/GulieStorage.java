package gulie;

import gulie.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the saving and loading of files for Gulie.
 */
public class GulieStorage {
    private String savepath;
    private GulieUi ui;

    /**
     * A constructor for GulieStorage.
     * @param ui
     * @param savepath The file to be saved to and loaded from.
     */
    public GulieStorage(GulieUi ui, String savepath) {
        this.savepath = savepath;
        this.ui = ui;
    }

    /**
     * Saves the given GulieTasklist to the save file.
     * @param tasklist
     * @throws GulieException If there is an IOException.
     */
    public void save(GulieTasklist tasklist) throws GulieException {
        try {
            File save = new File(savepath);
            save.mkdirs();
            save.delete();
            save.createNewFile();
            FileWriter fw = new FileWriter(save);
            for (Task t: tasklist) {
                fw.write(t.toSaveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new GulieException("Error: " + e.getMessage());
        }
    }

    /**
     * Loads a GulieTasklist from the save file.
     * @return
     */
    public GulieTasklist load() {
        GulieTasklist tasklist = new GulieTasklist();
        try {
            File save = new File(savepath);
            if (!save.exists()) {
                return tasklist;
            }
            Scanner scanner = new Scanner(save);
            while (scanner.hasNextLine()) {
                try {
                    tasklist.store(Task.fromSaveString(scanner.nextLine()));
                } catch (GulieException ge) {
                    ui.error(ge);
                }
            }
            scanner.close();
        } catch (IOException e) {
            ui.error(new GulieException("Error loading data. Ensure I have read permissions for the data file."));
        }
        return tasklist;
    }
}
