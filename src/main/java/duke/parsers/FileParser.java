package duke.parsers;

import duke.tasks.Event;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

/**
 * Class to parse text file and generates a TaskList for modifications from the user.
 */
public class FileParser {

    public File file;
    public TaskList taskList;

    public FileParser(File file) {
        this.file = file;
        this.taskList = new TaskList();
    }

    /**
     * Parses file object
     * @param file File object to be passed
     * @throws FileNotFoundException if file object is not found.
     */
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
            if (taskType.equals("T")) {
                taskList.add(new ToDo(taskName, isDone, "T"));
            } else if (taskType.equals("D")) {
                LocalDate start = DateTimeParser.stringToDT(taskInfo[3]);
                taskList.add(new Deadline(taskName, start, isDone,"D"));
            } else {
                LocalDate start = DateTimeParser.stringToDT(taskInfo[3]);
                LocalDate end = DateTimeParser.stringToDT(taskInfo[4]);
                taskList.add(new Event(taskName, start, end, isDone, "E"));
            }
        }
        s.close();

    }

    /**
     * Returns the class's task list
     * @return task list to be returned.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }


}
