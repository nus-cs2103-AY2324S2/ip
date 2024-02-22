package duke;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import duke.parsers.DateTimeParser;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * A class to modify the txt file at the file path after receiving a taskList
 */
public class Storage {
    String toothlessFilePath;
    TaskList taskList;

    public Storage(TaskList taskList) {
        this.taskList = taskList;
        this.toothlessFilePath = "data/toothless.txt";
    }

    /**
     * Creates new file if file does not exist at path. Else, modifies the file at the path.
     * @throws IOException if file cannot be created at the path
     */
    public void store() throws IOException{
        String filePathOld = "./data/toothless.txt";
        File oldFile = new File(filePathOld);
        String filePathNew = "./data/toothless.txt";
        File newFile = new File(filePathNew);
        try {
            boolean fileCreated = newFile.createNewFile();

        } catch (IOException e) {
            System.err.println("Error creating the file: " + e.getMessage());
            e.printStackTrace();
        }
        FileWriter fw = new FileWriter(filePathNew);
        for (Task t : taskList.getTasksList()) {
            String textToAdd = "";
            String taskType = t.getTaskType();
            if (taskType.equals("T")) {
                String name = t.getTaskName();
                String isDone = "0";
                if (t.getIsDone()) {
                    isDone = "1";
                }
                textToAdd += (taskType + " | " + isDone + " | " + name + "\n");
                fw.write(textToAdd);
                //System.out.println("1");
            } else if (taskType.equals("D")) {
                String name = t.getTaskName();
                String deadline = DateTimeParser.dtToString(t.getDeadline());
                String isDone = "0";
                if (t.getIsDone()) {
                    isDone = "1";
                }
                textToAdd += (taskType + " | " + isDone + " | " + name + " | " + deadline + "\n");
                fw.write(textToAdd);
            } else if (taskType.equals("E")) {
                String name = t.getTaskName();
                String start = DateTimeParser.dtToString(t.getStart());
                String end = DateTimeParser.dtToString(t.getEnd());
                String isDone = "0";
                if (t.getIsDone()) {
                    isDone = "1";
                }
                textToAdd += (taskType + " | " + isDone + " | " + name + " | " + " from " + start + " to " + end + "\n");
                fw.write(textToAdd);
            }
        }
        //System.out.println("1");
        fw.close();
        /*
        Path source = Paths.get("data/temp.txt");
        Path destination = Paths.get("data/toothless.txt");
        //oldFile.delete();
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        */
    }
}
