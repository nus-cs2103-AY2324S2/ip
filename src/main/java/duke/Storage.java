package duke;
import java.io.File;
import java.io.IOException;


public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public void loadData(TaskList taskList, Ui ui) {
        File file = this.retrievFile(ui);
        taskList.loadList(file, ui);
    }

    public void saveData(TaskList taskList, Ui ui) {
        File file = this.retrievFile(ui);
        taskList.saveList(file);
    }


    public File retrievFile(Ui ui) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
            ui.add("Created data folder as none was found");
        }
        try {
            if (file.createNewFile()) {
                ui.add("Created linus.txt to read files from");
            } else {
                ui.add("Retrieving file...");
            }
        } catch (IOException e) {
            ui.add("Could not create file :/");
        }
        ui.print();
        return file;
    }

}