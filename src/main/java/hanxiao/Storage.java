package hanxiao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import hanxiao.task.Deadline;
import hanxiao.task.Event;
import hanxiao.task.Task;
import hanxiao.task.Todo;

/**
 * Class for file management
 */
public class Storage {
    private File taskFile;

    /**
     * Constructor for storage.
     *
     * @param filePath Path of a file.
     */
    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    private Task readTask(String taskLine) {
        String[] token = taskLine.split(",");

        if (token[0].equals("T")) {
            return handleTodo(token);
        } else if (token[0].equals("D")) {
            return handleDeadline(token);
        } else if (token[0].equals("E")) {
            return handleEvent(token);
        } else {
            return null;
        }
    }

    private Todo handleTodo(String[] token) {
        if (token.length == 3) {
            Todo temp = new Todo(token[2]);
            checkAndSetDone(token, temp);
            return temp;
        }
        String tagString = token[3];
        ArrayList<String> tagList = getTags(tagString);
        Todo temp = new Todo(token[2], tagList);
        checkAndSetDone(token, temp);
        return temp;
    }

    private Deadline handleDeadline(String[] token) {
        if (token.length == 4) {
            Deadline temp = new Deadline(token[2], LocalDate.parse(token[3]));
            checkAndSetDone(token, temp);
            return temp;
        }
        String tagString = token[4];
        ArrayList<String> tagList = getTags(tagString);
        Deadline temp = new Deadline(token[2], tagList, LocalDate.parse(token[3]));
        checkAndSetDone(token, temp);
        return temp;
    }

    private Event handleEvent(String[] token) {
        if (token.length == 5) {
            Event temp = new Event(token[2], LocalDate.parse(token[3]), LocalDate.parse(token[4]));
            checkAndSetDone(token, temp);
            return temp;
        }
        String tagString = token[5];
        ArrayList<String> tagList = getTags(tagString);
        Event temp = new Event(token[2], tagList, LocalDate.parse(token[3]), LocalDate.parse(token[4]));
        checkAndSetDone(token, temp);
        return temp;
    }

    private ArrayList<String> getTags(String tagString) {
        ArrayList<String> tagList = new ArrayList<>();
        String[] tagToken = tagString.split("#");
        if (tagToken.length > 1) {
            for (int i = 1; i < tagToken.length; i++) {
                tagList.add(tagToken[i]);
            }
        }
        return tagList;
    }

    private void checkAndSetDone(String[] token, Task temp) {
        if (token[1].equals("1")) {
            temp.setDone();
        }
    }

    private String writeTask(Task t) {
        String taskLine = "";
        String icon = t.getTaskTypeIcon();
        int status = t.getStatus() ? 1 : 0;
        String description = t.getDescription();
        String tags = t.printTags();
        if (icon.equals("T")) {
            taskLine = String.format("%s,%s,%s,%s\n", icon, status, description, tags);
        } else if (icon.equals("D")) {
            taskLine = String.format("%s,%s,%s,%s,%s\n", icon, status, description, ((Deadline) t).getBy(), tags);
        } else if (icon.equals("E")) {
            taskLine = String.format("%s,%s,%s,%s,%s,%s\n",
                    icon, status, description, ((Event) t).getFrom(), ((Event) t).getTo(), tags);
        }
        return taskLine;
    }

    /**
     * Loads the file into a list.
     *
     * @return An ArrayList of Tasks.
     * @throws FileNotFoundException if file not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(this.taskFile);
        while (sc.hasNext()) {
            String taskLine = sc.nextLine();
            tasks.add(readTask(taskLine));
        }
        return tasks;
    }

    /**
     * Writes tasks to file.
     *
     * @param tasks The task List we want to write into file.
     * @throws IOException If file not exist.
     */
    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.taskFile);
        for (Task t : tasks) {
            fw.write(writeTask(t));
        }
        fw.close();
    }

    public void resetStorage(String filePath) {
        this.taskFile = new File(filePath);
    }
}
