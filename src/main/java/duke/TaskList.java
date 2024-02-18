package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void printTaskCount() {
        int count = getTaskCount();
        System.out.println("Now you have " + count + " " + (count == 1 ? "task" : "tasks") + " in the list.");
    }

    public TaskList findTasks(String query) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(query)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    public String getTaskDescription(int index) {
        return tasks.get(index).toString();
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkTaskAsDone(int index) {
        tasks.get(index).unmarkAsDone();
    }

    public void clear() {
        tasks.clear();
    }

    public void loadFromSavedData(List<String> lines, Parser parser) {
        clear();
        try {
            for (String command : lines) {
                parser.parse(command).executeSilently(this);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            clear();
        }
    }

    public List<String> getSaveData() {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            lines.add(tasks.get(i).serializeToCommand(i));
        }
        return lines;
    }
}
