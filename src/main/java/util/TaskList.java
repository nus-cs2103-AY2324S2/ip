package util;

import exceptions.ChatBotException;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskList {
    private static final int MAX_TASKS = 100;
    private static final String FILE_PATH = "./data/duke/.txt";
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        if (this.tasks.size() < MAX_TASKS) {
            this.tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + this.tasks.get(this.tasks.size() - 1));
            System.out.println("Now you have " + this.tasks.size() + " task" +
                    (this.tasks.size() == 1 ? "" : "s") + " in the list");
        }
    }

    public void deleteTask(int number) throws ChatBotException {
        if (this.tasks.size() == 0) {
            throw new ChatBotException("Oops! There are no tasks in the list.");
        }
        if (number > this.tasks.size()) {
            throw new ChatBotException("Oops! Number entered does not exist in the list.");
        }
        Task remainingTasks = this.tasks.remove(number - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + remainingTasks.toString());
        System.out.println("Now you have " + this.tasks.size() + " task" +
                (this.tasks.size() == 1 ? "" : "s") + " in the list");
    }

    public void markTask(int index) throws ChatBotException {
        if (index < 0) {
            throw new ChatBotException("Oops! Number entered cannot be negative.");
        }
        if (index == 0 || index > this.tasks.size()) {
            throw new ChatBotException("Oops! Number entered does not exist in the list.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + currTask.toString());
    }

    public void unmarkTask(int index) throws ChatBotException {
        if (index < 0) {
            throw new ChatBotException("Oops! Number entered cannot be negative.");
        }
        if (index == 0 || index > this.tasks.size()) {
            throw new ChatBotException("Oops! umber entered does not exist in the list.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + currTask.toString());
    }

    public void listTasks() {
        if (this.tasks.size() == 0) {
            //throw new exceptions.ChatBotException("Oops! The task list is currently empty.");
            System.out.println("Oops! The task list is currently empty.");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                System.out.println((i + 1) + "." + currTask.toString());
            }
        }
    }

    public void saveToFile(TaskList taskList) {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            for (Task task : taskList.getTasks()) {
                fw.write(task.toStringForFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops! There was an error saving to file.");
        }
    }

    public void loadFromFile(TaskList taskList) throws IOException {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task task = Parser.parseTasksFromFile(taskString);
                taskList.getTasks().add(task);
            }
        } catch (IOException e) {
            System.out.println("Oops! There was an error loading the file.");
        } catch (ChatBotException e) {
            System.out.println("Oops! There was an error parsing the file.");
        }
    }
}

