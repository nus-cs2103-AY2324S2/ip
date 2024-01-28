package saopig.task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final String FILE_PATH = "./data/saopigTaskList.txt";
    private static final String FILE_DIRECTORY = "./data";
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTodoTask(Todo task) {
        this.tasks.add(task);
    }

    public void addDeadlineTask(Deadline task) {
        this.tasks.add(task);
    }

    public void addEventTask(Event task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

}
