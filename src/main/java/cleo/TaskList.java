package cleo;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for removal";
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> sortedTasks = new ArrayList<>(this.tasks);
        Collections.sort(sortedTasks, new TaskComparator());
        return sortedTasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasksOnDate(String dateString) throws DukeException {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use d/M/yyyy.");
        }

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDate deadlineDate = deadline.getBy().toLocalDate();
                if (deadlineDate.equals(date)) {
                    filteredTasks.add(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (!event.getFrom().isAfter(date) && !event.getTo().isBefore(date)) {
                    filteredTasks.add(event);
                }
            }
        }
        Collections.sort(filteredTasks, new TaskComparator());
        return filteredTasks;
    }
    public ArrayList<Task> findTasks(String... keywords) {

        ArrayList<Task> filteredTasks = tasks.stream()
                .filter(task -> Arrays.stream(keywords)
                        .anyMatch(keyword -> task.getDescription().contains(keyword)))
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(filteredTasks, new TaskComparator());
        return filteredTasks;
    }

}
