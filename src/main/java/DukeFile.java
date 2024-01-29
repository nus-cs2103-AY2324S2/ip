import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DukeFile {
    private final String filePath = "./data/duke.txt";
    private File file;

    public DukeFile() throws IOException {
        this.file = new File(this.filePath);

        if (!this.file.getParentFile().exists())
            this.file.getParentFile().mkdirs();
        if (!this.file.exists())
            this.file.createNewFile();
    }

    /**
     * read content of the file into task list.
     * @return task list read from file
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Task> readFile() throws FileNotFoundException{
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currentTaskString = s.nextLine();
            Task newTask = this.stringToTask(currentTaskString);
            taskList.add(newTask);
        }
        return taskList;
    }

    /**
     * write all the tasks in the task list to the file stored
     * @param tasksToWrite tasks to be written to the file
     * @throws IOException exception throw when try to write to the file.
     */
    public void writeTasksToFile(ArrayList<Task> tasksToWrite) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for(Task t: tasksToWrite) {
            fw.write(t.inFileStringFormat() + "\n");
        }
        fw.close();
    }

    /**
     * Change task from string format to task object
     * @param str String format of the task
     * @return The respective task object
     */
    private Task stringToTask(String str) {
        String[] strSplit = str.split("\\|");
        Boolean status = strSplit[1].equals("1");
        String detail = strSplit[2];
        Task task = new Task(status, detail);
        switch (strSplit[0]) {
            case "T":
                task = new Todo(status, detail);
                break;
            case "D":
                String by = strSplit[3];
                task = new Deadline(status, detail, by);
                break;
            case "E":
                String from = strSplit[3];
                String to = strSplit[4];
                task = new Event(status, detail, from, to);
                break;
        }
        return task;
    }




}
