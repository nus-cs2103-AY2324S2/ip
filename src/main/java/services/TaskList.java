package services;

import exceptions.DukeException;
import tasks.Task;
import services.parser.Parser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskList {
    private static final int MAX_ITEMS = 100;
    private List<Task> tasks;
    private final String FILE_PATH = "./data/duke.txt";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + this.tasks.get(this.tasks.size() - 1));
        System.out.println(
                "\tNow you have " + this.tasks.size() + " task" +
                        (this.tasks.size() == 1 ? "" : "s") + " in the list");
    }

    public void deleteTask(int index) throws DukeException {
        if (this.tasks.size() == 0) {
            throw new DukeException("tasks.Task index is out of range.");
        }
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Index out of range");
        }
        Task deletedTask = this.tasks.remove(index - 1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + deletedTask.toString());
        System.out.println(
                "\tNow you have " + this.tasks.size() + " task" +
                        (this.tasks.size() == 1 ? "" : "s") + " in the list");
    }

    public void markTask(int index) throws DukeException {
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("tasks.Task index is out of range.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        currTask.toString();
    }

    public void unmarkTask(int index) throws DukeException {
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("tasks.Task index is out of range.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsUndone();
        System.out.println("\tOK, I've marked this task as not done yet:");
        currTask.toString();
    }

    public void listTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("\tThe task list is empty.");
        } else {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                System.out.println("\t" + (i + 1) + "." + currTask.toString());
            }
        }
    }

    private void loadTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task currTask = Parser.parseTaskFromString(taskString);
                this.tasks.add(currTask);
            }
        } catch (IOException e) {
            System.out.println("Error occurred when writing to file");
        } catch (DukeException e) {
            System.out.println("Error occurred when parsing file");
        }
    }
    private void saveTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (Task task: this.tasks) {
                fileWriter.write(task.fileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error occurred when writing to file");
        }
    }
}
