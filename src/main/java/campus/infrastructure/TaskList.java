package campus.infrastructure;

import java.util.ArrayList;
import java.util.List;

import campus.exceptions.CampusException;
import campus.tasks.Deadline;
import campus.tasks.Event;
import campus.tasks.Task;
import campus.tasks.ToDos;

/**
 * TaskList Class handles all CRUD actions related to the Task Class, it also contains the List of 'Task'
 * type field to store the various tasks in the list and does List Manipulation operations on them
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Updates the current List of Task type given a List of String type input read from the saved txt file in memory
     * @param listOfStrings List of String format
     * @throws CampusException In the event that the file is corrupted and the List of String type does not match the
     *      accepted txt file formatting for its data
     */
    public void updateListFromFile(List<String> listOfStrings) throws CampusException {
        if (listOfStrings == null) {
            return;
        }

        List<Task> task = new ArrayList<>();

        for (String string : listOfStrings) {
            String[] parts = string.split("\\|");
            String typeOfTask = parts[0].trim();
            switch (typeOfTask) {
            case "T":
                if (parts.length != 3) {
                    throw new CampusException("File is Corrupted, Check Formatting for 'T'");
                } else {
                    String todoName = parts[2].trim();
                    Boolean isCompleted = parts[1].trim().equals("1");
                    task.add(new ToDos(todoName, isCompleted));
                }
                break;
            case "D":
                if (parts.length != 4) {
                    throw new CampusException("File is Corrupted, Check Formatting for 'D'");
                } else {
                    String deadlineName = parts[2].trim();
                    Boolean isCompleted = parts[1].trim().equals("1");
                    String deadlineEndTime = parts[3].trim();
                    task.add(new Deadline(deadlineName, isCompleted, deadlineEndTime));
                }
                break;
            case "E":
                if (parts.length != 5) {
                    throw new CampusException("File is Corrupted, Check Formatting for 'E'");
                } else {
                    String eventName = parts[2].trim();
                    Boolean isCompleted = parts[1].trim().equals("1");
                    String eventStartTime = parts[3].trim();
                    String eventEndTime = parts[4].trim();
                    task.add(new Event(eventName, isCompleted, eventStartTime, eventEndTime));
                }
                break;
            default:
                break;
            }
        }
        this.tasks = task;
    }

    public List<Task> getListOfTasks() {
        return this.tasks;
    }

    public TaskList getTaskListWhere(String string) {
        TaskList temp = new TaskList();

        for (Task task : this.tasks) {
            if (task.taskName.contains(string)) {
                temp.addTask(task);
            }
        }
        return temp;
    }

    public Task getIthTaskInteger(int index) {
        assert(index < this.tasks.size());
        return this.tasks.get(index);
    }

    public Task getIthTaskString(String userInput) {
        assert(userInput != null);
        String listNumber = userInput.substring(userInput.length() - 1);
        int index = Integer.parseInt(listNumber) - 1;
        return this.tasks.get(index);
    }

    public int getListSize() {
        return tasks.size();
    }
    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    public void markDone(Task task) {
        task.markComplete();
        assert(task.completed);
    }

    public void markUndone(Task task) {
        task.markIncomplete();
        assert(!task.completed);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }
}
