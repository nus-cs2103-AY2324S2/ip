import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filepath;
    private final File file;
    Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }

    public ArrayList<String> loadFile() throws LukeException {
        ArrayList<String> taskList = new ArrayList<>();
        try {
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }

        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data != "") {
                    taskList.add(data);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return taskList;
    }

    public void saveFile(TaskList taskListObject) {
        try {
            ArrayList<Task> taskList = taskListObject.getTaskList();
            FileWriter writer = new FileWriter(filepath);
            for (Task task : taskList) {
                writer.write(task.toString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("Successfully saved file to local.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving.");
            e.printStackTrace();
        }
    }
}
