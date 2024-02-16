package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import task.TaskList;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;
import exception.DukeException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Adds text into fileName
     * 
     * @param list List of Task
     */
    public void writeArrayListToFile(TaskList list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task element : list.getList()) {
                writer.write(element.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.ready()) {
                String task = reader.readLine();
                char taskType = task.charAt(1);
                char taskStatus = task.charAt(4);
                boolean isDone;
                if (taskStatus == 'X') {
                    isDone = true;
                } else {
                    isDone = false;
                }
                String entireTask = task.substring(7);
                String taskDetail;
                switch (taskType) {
                    case 'T':
                        taskList.add(new Todo(entireTask, isDone));
                        break;
                    case 'D':
                        int startIndex = entireTask.indexOf("(by:");
                        int endIndex = entireTask.length() - 1;
                        String byDetails = entireTask.substring(startIndex + 5, endIndex);
                        taskDetail = entireTask.substring(0, startIndex - 1);
                        taskList.add(new Deadline(taskDetail, byDetails, isDone));
                        break;
                    case 'E':
                        int toIndex = entireTask.indexOf("to:");
                        int fromIndex = entireTask.indexOf("(from:");
                        int toLastIndex = entireTask.length() - 1;
                        taskDetail = entireTask.substring(0, fromIndex - 1);
                        String toText = entireTask.substring(toIndex + 4, toLastIndex);
                        String fromText = entireTask.substring(fromIndex + 7, toIndex - 1);
                        taskList.add(new Event(taskDetail, fromText, toText, isDone));
                        break;
                }
            }
        } catch (IOException e) {
            throw new DukeException();
        }
        return taskList;
    }
}
