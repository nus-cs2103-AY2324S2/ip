package storage;

import exception.TobiasException;
import parser.Parser;
import task.TaskList;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    private String filePath;

    /**
     * Constructor for a Storage.
     *
     * @param filePath The relative string file path of the saved data.
     * */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if there is a local saved txt file at the filePath.
     * If there exists no such .txt file, it will create a new save file at the filePath.
     * */
    public void createLocalStorage() {
        try {
            File file = new File(filePath);

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (file.createNewFile()) {
                System.out.println("I have made a new save file for ya : " + file.getAbsolutePath());
            } else {
                System.out.println("You seem to have an existing save file, YAY : " + file.getAbsolutePath());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void todoExtractor(String data, TaskList tasks) throws TobiasException {
        boolean isDone = Integer.parseInt(data.substring(8,9)) == 1;

        int desc = data.indexOf("|desc");


        int tag = data.indexOf("|tag");

        if (tag == -1) {
            String description = data.substring(desc+5);
            Task newTask = new ToDo(description, isDone);
            tasks.addToList(newTask);
        } else {
            String description = data.substring(desc+5, tag);

            String tagString = data.substring(tag+4);

            String[]tags = tagString.split("_");

            Task newTask = new ToDo(description, isDone);

            for (String t : tags){
                newTask.addTag(t);
            }

            tasks.addToList(newTask);
        }
    }

    public static void deadlineExtractor(String data, TaskList tasks) throws TobiasException {
        boolean isDone = Integer.parseInt(data.substring(8,9)) == 1;

        int desc = data.indexOf("|desc");
        int by = data.indexOf("|by");
        int tag = data.indexOf("|tag");

        if (tag == -1) {
            String description = data.substring(desc+5, by);
            String byDate = data.substring(by+3);
            LocalDateTime dd = Parser.dateFromString(byDate);
            Task newTask = new Deadline(description, isDone, dd);
            tasks.addToList(newTask);
        } else {
            String description = data.substring(desc+5, by);
            String byDate = data.substring(by+3, tag);
            String tagString = data.substring(tag+4);
            LocalDateTime dd = Parser.dateFromString(byDate);
            String[]tags = tagString.split("_");

            Task newTask = new Deadline(description, isDone, dd);

            for (String t : tags){
                newTask.addTag(t);
            }

            tasks.addToList(newTask);
        }
    }

    public static void eventExtractor(String data, TaskList tasks) throws TobiasException {
        boolean isDone = Integer.parseInt(data.substring(8,9)) == 1;

        int desc = data.indexOf("|desc");
        int from = data.indexOf("|from");
        int to = data.indexOf("|to");
        int tag = data.indexOf("|tag");

        if (tag == -1) {
            String description = data.substring(desc+5, from);
            String fromDate = data.substring(from+5, to);
            String toDate = data.substring(to+3);

            LocalDateTime f = Parser.dateFromString(fromDate);
            LocalDateTime t = Parser.dateFromString(toDate);

            Task newTask = new Event(description, isDone, f, t);
            tasks.addToList(newTask);
        } else {
            String description = data.substring(desc+5, from);
            String fromDate = data.substring(from+5, to);
            String toDate = data.substring(to+3, tag);

            String tagString = data.substring(tag+4);
            String[]tags = tagString.split("_");

            LocalDateTime f = Parser.dateFromString(fromDate);
            LocalDateTime t = Parser.dateFromString(toDate);

            Task newTask = new Event(description, isDone, f, t);

            for (String tt : tags){
                newTask.addTag(tt);
            }

            tasks.addToList(newTask);
        }
    }

    /**
     * Reads data of commands from the local save file and adds them to the given TaskList.
     *
     * @param data String of commands.
     * @param tasks TaskList.
     * @throws TobiasException If the given data contains corrupted commands.
     * */
    public static void localToList(String data, TaskList tasks) throws TobiasException {
        if (data.startsWith("T")) {
            todoExtractor(data, tasks);
        } else if (data.startsWith("D")) {
            deadlineExtractor(data, tasks);
        } else if (data.startsWith("E")) {
            eventExtractor(data, tasks);
        } else {
            throw new TobiasException("OH NO, someone has ruined your save file :(");
        }
    }

    /**
     * Reads the local saved .txt file at the filePath.
     * Calls localToList to interpret and add the commands that have been saved in the local .txt file.
     * */
    public TaskList localToCurrent() {
        TaskList tasks = new TaskList();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                localToList(s.nextLine(), tasks);
            }
            s.close();
        } catch (TobiasException tE) {
            tE.printMessage();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Takes in a TaskList of tasks and stores it to the local save .txt file.
     *
     * @param tasks TaskList containing the tasks that need to be saved.
     * */
    public void storeToLocal(TaskList tasks) {
        String result = tasks.saveMechanism();

        try {
            FileWriter fw = new FileWriter("data/tobias.txt");
            fw.write(result);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
