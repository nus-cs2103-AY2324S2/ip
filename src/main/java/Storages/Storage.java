package Storages;

import Tasks.Task;
import Tasks.TaskList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private File file;

    public Storage(String filename) {
        this.file = new File(filename);
    }

    public ArrayList<Task> loadFile() throws FileNotFoundException, IOException {
        ArrayList<Task> list = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(this.file));

        String next;
        while((next = br.readLine()) != null) {
            int type = next.indexOf("type: ");
            int isDone = next.indexOf("isDone: ");
            int content = next.indexOf("content: ");
            String done = next.substring(isDone + 8, isDone + 9);
            Task newTask = new Task(next.substring(content + 9), next.substring(type + 6, type + 7), done.equals("t"));
            list.add(newTask);
        }
        return list;
    }

    public void updateFile(TaskList list) {
        String filename = "data/Fredricksen.txt";
        File file = new File(filename);

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
