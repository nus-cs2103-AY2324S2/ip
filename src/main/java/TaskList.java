import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws AtlasException {
        // Implement deletion logic
        if (index < 0 || index >= tasks.size()) {
            throw new AtlasException("Task number " + (index + 1) + " does not exist in the list.");
        }
        tasks.remove(index);

    }

    public void markTask(int i) throws AtlasException {
        if (i < 0 || i >= tasks.size()) {
            throw new AtlasException("Task number " + (i + 1) + " does not exist.");
        }
        tasks.get(i).toggle();

    }

    public void unmarkTask(int i) throws AtlasException {
        if (i < 0 || i >= tasks.size()) {
            throw new AtlasException("Task number " + (i + 1) + " does not exist.");
        }
        tasks.get(i).toggle();

    }

    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(date)) {
                    tasksOnDate.add(task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                LocalDate startDate = event.getStart().toLocalDate();
                LocalDate endDate = event.getEnd().toLocalDate();
                if ((date.isEqual(startDate) || date.isAfter(startDate)) &&
                        (date.isEqual(endDate) || date.isBefore(endDate))) {
                    tasksOnDate.add(task);
                }
            }
        }
        return tasksOnDate;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
