package harvard;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import harvard.tasks.Task;
import harvard.tasks.Event;
import harvard.tasks.Deadline;
import harvard.tasks.Todo;

public class TaskList {
    List<Task> taskList = new ArrayList<>();

    public TaskList(BufferedReader br) {
        populateTaskList(br);
    }

    public TaskList() {
    }

    public String printString(int index) {
        return taskList.get(index).toString();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void delete(int index) {
        taskList.remove(index);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void mark(int index) {
        this.taskList.get(index).mark();
    }

    public void unmark(int index) {
        this.taskList.get(index).unmark();
    }
    public void populateTaskList(BufferedReader buffReader) {
        try {
            String line;
            while (( line = buffReader.readLine()) != null) {
                String taskType = line.split(",")[0];
                Boolean isDone =  line.split(",")[1].equals("0") ? false : true;
                if (taskType.equals("T") ) {
                    taskList.add(new Todo(line.split(",")[2], isDone));
                } else if (taskType.equals("D")) {
                    taskList.add(new Deadline(line.split(",")[2], LocalDate.parse(line.split(",")[3]), isDone));
                } else {
                    taskList.add(new Event(line.split(",")[2], LocalDate.parse(line.split(",")[3]), LocalDate.parse(line.split(",")[4]), isDone));
                }
            }
        } catch (IOException e) {

        }

    }

}