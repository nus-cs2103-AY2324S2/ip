package Utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Exceptions.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

import Task.Task;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public void storeTaskList(ArrayList<Task> list) throws IOException {
        File taskList = new File(filePath);
        if (!taskList.getParentFile().exists()) {
            taskList.getParentFile().mkdirs();
        }
        if (!taskList.exists()) {
            taskList.createNewFile();
        }
        FileWriter fw = new FileWriter(taskList);
        if (list.size() == 1) {
            fw.write("There is 1 task in the list.\n");
        } else {
            fw.write("There are " + list.size() + " tasks in the list.\n");
        }
        for (Task t : list) {
            fw.append(t.toString() + "\n");
        }
        fw.close();
    }

    public ArrayList<Task> load() throws IOException, DukeException {
        File taskList = new File(filePath);
        if (!taskList.getParentFile().exists()) {
            taskList.getParentFile().mkdirs();
        }
        if (!taskList.exists()) {
            taskList.createNewFile();
        }
        Scanner sc = new Scanner(taskList);
        if (sc.hasNextLine()) {
            sc.nextLine();
        }
        ArrayList<Task> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line == "\n") {
                break;
            }
            Task t = Parser.parseFile(line);
            list.add(t);
        }
        return list;
    }
}
