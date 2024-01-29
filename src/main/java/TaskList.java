import java.util.ArrayList;
import java.util.List;

/**
 * TaskList Class handles all CRUD actions related to the Task Class, it also contains the List<Task> field to store
 * the various tasks in the list and does List Manipulation operations on them
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Updates the current List<Tasks> given a List<String> input read from the saved txt file in memory
     * @param listOfStrings List<String> format
     * @throws CampusException In the event that the file is corrupted and the List<String> does not match the
     * accepted txt file formatting for its data
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
                        Boolean completed = parts[1].trim().equals("1");
                        task.add(new ToDos(todoName, completed));
                    }
                    break;
                case "D":
                    if (parts.length != 4) {
                        throw new CampusException("File is Corrupted, Check Formatting for 'D'");
                    } else {
                        String deadlineName = parts[2].trim();
                        Boolean completed = parts[1].trim().equals("1");
                        String deadlineEndTime = parts[3].trim();
                        task.add(new Deadline(deadlineName, completed, deadlineEndTime));
                    }
                    break;
                case "E":
                    if (parts.length != 5) {
                        throw new CampusException("File is Corrupted, Check Formatting for 'E'");
                    } else {
                        String eventName = parts[2].trim();
                        Boolean completed = parts[1].trim().equals("1");
                        String eventStartTime = parts[3].trim();
                        String eventEndTime = parts[4].trim();
                        task.add(new Event(eventName, completed, eventStartTime, eventEndTime));
                    }
            }
        }
        this.tasks = task;
    }

    public List<Task> getListOfTasks() {
        return this.tasks;
    }

    public Task getIthTaskInteger(int index) {
        return this.tasks.get(index);
    }

    public  Task getIthTaskString(String userInput) {
        String listNumber = userInput.substring(userInput.length() - 1);
        int index = Integer.parseInt(listNumber) - 1;
        return this.tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
    public void delete(Task task) {
        this.tasks.remove(task);
    }

    public void markDone(Task task) {
        task.markComplete();
    }

    public void markUndone(Task task) {
        task.markIncomplete();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }
}
