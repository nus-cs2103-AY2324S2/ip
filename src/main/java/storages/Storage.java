package storages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tasks.taskType.Deadline;
import tasks.taskType.Event;
import tasks.taskType.Task;
import tasks.taskType.ToDo;
import tasks.TaskList;

public class Storage {
    private File file;

    public Storage(String filename) {
        this.file = new File(filename);
    }

    public void createNewTask(String taskType, String content, boolean isDone, TaskList list) {
        if (taskType.equals("D")) {
            Deadline newTask = new Deadline(content, taskType, isDone);
            list.addTask(newTask);
        } else if (taskType.equals("T")) {
            ToDo newTask = new ToDo(content, taskType, isDone);
            list.addTask(newTask);
        } else if (taskType.equals("E")) {
            Event newTask = new Event(content, taskType, isDone);
            list.addTask(newTask);
        }
    }

    /**
     * Returns an ArrayList of Task based on the data
     * stored in the Fredricksen.txt file.
     *
     * @return an ArrayList of Task
     * @throws IOException if file cannot be opened or read.
     */
    public TaskList loadFile() throws IOException {
        TaskList list = new TaskList();
        BufferedReader br = new BufferedReader(new FileReader(this.file));

        String next;
        while ((next = br.readLine()) != null) {
            int type = next.indexOf("type: ");
            int isDone = next.indexOf("isDone: ");
            int content = next.indexOf("content: ");
            boolean done = next.substring(isDone + 8, content - 1).equals("true");
            createNewTask(next.substring(type + 6, type + 7), next.substring(content + 9), done, list);
        }
        return list;
    }

    public void updateFile(TaskList list) {
        String filename = "data/Fredricksen.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < list.size(); i++) {
                Task task = list.getTask(i);
                String type = task.getType();
                String content = task.getTask();
                boolean isDone = task.getDone();
                bw.write("type: " + type + " isDone: " + isDone + " content: " + content);
                bw.newLine();
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
