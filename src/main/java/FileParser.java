import Tasks.Event;
import Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import Tasks.Deadline;
import Tasks.ToDo;
import Tasks.Event;
public class FileParser {

    public File file;
    public TaskList taskList;

    public FileParser(File file) {
        this.file = file;
        this.taskList = new TaskList();
    }

    public void parseFile(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String next = s.nextLine();
            String[] taskInfo = next.split(" \\| ");
            String taskType = taskInfo[0];
            Boolean isDone = false;
            if (taskInfo[1].equals("1")) {
                isDone = true;
            }
            String taskName = taskInfo[2];
            String start, end = "";
            if (taskType.equals("T")) {
                taskList.add(new ToDo(taskName, isDone, "T"));
            } else if (taskType.equals("D")) {
                start = taskInfo[3];
                taskList.add(new Deadline(taskName, start, isDone,"D"));
            } else {
                start = taskInfo[3];
                end = taskInfo[4];
                taskList.add(new Event(taskName, start, end, isDone, "E"));
            }
        }
        s.close();

    }

    public TaskList getTaskList() {
        return this.taskList;
    }


}
