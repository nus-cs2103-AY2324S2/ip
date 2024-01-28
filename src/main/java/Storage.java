import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;
import Tasks.Task;
import Tasks.ToDo;
import Tasks.Event;
import Tasks.Deadline;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
public class Storage {
    String toothlessFilePath;
    TaskList taskList;

    public Storage(TaskList taskList) {
        this.taskList = taskList;
        this.toothlessFilePath = "data/toothless.txt";
    }

    public void store() throws IOException{
        String filePathOld = "data/toothless.txt";
        File oldFile = new File(filePathOld);
        String filePathNew = "data/temp.txt";
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
                String deadline = t.getDeadline();
                String isDone = "0";
                if (t.getIsDone()) {
                    isDone = "1";
                }
                textToAdd += (taskType + " | " + isDone + " | " + name + " | " + deadline + "\n");
                fw.write(textToAdd);
            } else if (taskType.equals("E")) {
                String name = t.getTaskName();
                String start = t.getStart();
                String end = t.getEnd();
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

        Path source = Paths.get("data/temp.txt");
        Path destination = Paths.get("data/toothless.txt");
        //oldFile.delete();
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

    }
}
