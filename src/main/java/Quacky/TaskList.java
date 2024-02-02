package Quacky;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }
    /*
    This method prints a given task at position i
     */
    public String printTask(int i){
        Task task = tasks.get(i);
        return task.toString();
    }
    public int taskNumber() {
        return tasks.size();
    }
    public void markCompleteTask(int i) throws QuackyException {
        try {
            Task task = tasks.get(i);
            task.markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new QuackyException("Quack. The task is not found");
        }
    }
    public void unmarkCompleteTask (int i){
        Task task = tasks.get(i);
        task.unmarkDone();
    }
    public void deleteTask(int i){
        tasks.remove(i);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size();i++){
            sb.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }
        return sb.toString();
    }


    protected String printSimplified() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toFileString()).append("\n");
        }

        String simplifiedString = sb.toString();
        return simplifiedString;

    }
    protected void writeToFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toFileString()).append("\n");
        }

        String textToAdd = sb.toString();
        System.out.println(this.printSimplified());
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(textToAdd);
        fw.close();
    }

    public void loadFromFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(" \\| ");
                Task task = null;

                switch (parts[0]) {
                    case "E":
                        task = new Event(parts[2], LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
                        break;
                    case "D":
                        task = new Deadline(parts[2], LocalDate.parse(parts[3]));
                        break;
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                }

                if (task != null) {
                    if (parts[1].equals("0")) {
                        task.markDone();
                    }
                    this.addTask(task);
                }
            }

            fileReader.close();
        } catch (IOException e) {
            System.out.println("An error has occurred");
        }
    }


    /**
     * Finds and returns a TaskList of tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A TaskList containing the tasks that match the keyword.
     */
    public TaskList findTasksByKeyword(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }
}
