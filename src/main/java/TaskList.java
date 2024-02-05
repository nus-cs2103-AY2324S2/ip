import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    private ArrayList<Task> taskList;
    private int noTasks;
    TaskList(ArrayList<String> saveTaskList) {
        this.taskList = new ArrayList<>();
        this.noTasks = 0;
        if (!saveTaskList.isEmpty()) {
            saveTaskList.forEach(this::loadToTaskList);
        }
    }

    TaskList() {
        this.taskList = new ArrayList<>();
        this.noTasks = 0;
    }

    private void loadToTaskList(String taskString) {
        String taskType = taskString.substring(1, 2);
        switch (taskType) {
            case "T":
                Todo todo = new Todo(taskString.substring(7).trim());
                taskList.add(todo);
                noTasks++;
                break;
            case "D":
                String[] deadlineSplit = taskString.split("by: ");
                String deadlineDescription = deadlineSplit[0].substring(7, deadlineSplit[0].length() - 1);
                String by = deadlineSplit[1].substring(0, deadlineSplit[1].length() - 1).trim();
                Deadline deadline = new Deadline(deadlineDescription, by);
                taskList.add(deadline);
                noTask++;
                break;
            case "E":
                String[] eventFirstSplit = taskString.split("from: ");
                String[] eventSecondSplit = eventFirstSplit[1].split(" to: ");
                String eventDescription = eventFirstSplit[0].substring(7, eventFirstSplit[0].length() - 1);
                String from = eventSecondSplit[0].trim();
                String to = eventSecondSplit[1].substring(0, eventSecondSplit[1].length() - 1).trim();
                Event event = new Event(eventDescription, from, to);
                taskList.add(event);
                noTasks++;
                break;
        }
    }

    public void markTask(int taskNo) {
        taskList.get(taskNo - 1).setToDone();
    }

    public void unmarkTask(int taskNo) {
        taskList.get(taskNo - 1).setToNotDone();
    }

    public void list() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
    }

    public void addTodo(String input) {
        Todo todo = new Todo(input.substring(5));
        taskList.add(todo);
        noTasks++;
    }

    public void addDeadline(String input) {
        String[] deadlineSplit = input.split("/by");
        String deadlineDescription = deadlineSplit[0].substring(9).trim();
        String by = deadlineSplit[1].trim();

        Deadline deadline = new Deadline(deadlineDescription, by);
        taskList.add(deadline);
        noTasks++;
    }

    public void addEvent(String input) {
        String[] eventSplitFrom = input.split("/from");
        String eventDescription = eventSplitFrom[0].substring(6);
        String[] eventSplitTo = eventSplitFrom[1].split("/to");
        String from = eventSplitTo[0].trim();
        String to = eventSplitTo[1].trim();
        Event event = new Event(eventDescription, from, to);

        taskList.add(event);
        noTasks++;
    }

    public void deleteEvent(int taskNo) {
        taskList.remove(taskNo - 1);
        noTasks--;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getNoTasks() {
        return noTasks;
    }
}
