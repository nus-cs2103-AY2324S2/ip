package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeCeption;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public void loadData(TaskList taskList, Ui ui) {
        try {
            File file = this.retrievFile(ui);
            ArrayList<String> dataStrings = this.dataToText(file);
            taskList.loadList(dataStrings);
        } catch (DukeCeption e) {
            ui.print(e.getMessage());
        }

    }

    public ArrayList<String> dataToText(File file) throws DukeCeption {
        try {
            ArrayList<String> dataStrings = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                dataStrings.add(line);
            }
            scanner.close();
            return dataStrings;
            } catch (FileNotFoundException e) {
                throw new DukeCeption("File is not found!");
            }
        }


    public void saveData(TaskList taskList, Ui ui) {
        File file = this.retrievFile(ui);
        ArrayList<String> dataToTexts = taskList.saveFormat();
        try {
            FileWriter writer = new FileWriter(file, false);
            for (String text : dataToTexts) {
                writer.write(text + "\n");
            }
            writer.close();
        } catch (IOException e) {
            ui.print(e.getMessage());
        }
    }


    public File retrievFile(Ui ui) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
            ui.print("Created data folder as none was found");
        }
        try {
            if (file.createNewFile()) {
                ui.print("Created linus.txt to read files from");
            }
        } catch (IOException e) {
            ui.print("Could not create file :/");
        }
        return file;
    }

}