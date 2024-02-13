package Luke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int noTasks;
    TaskList(ArrayList<String> saveTaskList) {
        this.taskList = new ArrayList<>();
        if (!saveTaskList.isEmpty()) {
            saveTaskList.forEach(this::loadToTaskList);
        }
        this.noTasks = this.taskList.size();
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
                break;
            case "D":
                String[] deadlineSplit = taskString.split("by: ");
                String deadlineDescription = deadlineSplit[0].substring(7, deadlineSplit[0].length() - 1);
                String by = deadlineSplit[1].substring(0, deadlineSplit[1].length() - 1).trim();
                Deadline deadline = new Deadline(deadlineDescription, by);
                taskList.add(deadline);
                break;
            case "E":
                String[] eventFirstSplit = taskString.split("from: ");
                String[] eventSecondSplit = eventFirstSplit[1].split(" to: ");
                String eventDescription = eventFirstSplit[0].substring(7, eventFirstSplit[0].length() - 1);
                String from = eventSecondSplit[0].trim();
                String to = eventSecondSplit[1].substring(0, eventSecondSplit[1].length() - 1).trim();
                Event event = new Event(eventDescription, from, to);
                taskList.add(event);
                break;
        }
    }

    public void markTask(int taskNo) {
        taskList.get(taskNo).setToDone();
    }

    public void unmarkTask(int taskNo) {
        taskList.get(taskNo).setToNotDone();
    }

    public void list() {
        for (int i = 0; i < noTasks; i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
    }

    public void addTodo(String description) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        noTasks++;
    }

    public void addDeadline(String deadlineDescription, String by) {
        Deadline deadline = new Deadline(deadlineDescription, by);
        taskList.add(deadline);
        noTasks++;
    }

    public void addEvent(String eventDescription, String from, String to) {
        Event event = new Event(eventDescription, from, to);
        taskList.add(event);
        noTasks++;
    }

    public Task deleteEvent(int taskNo) {
        Task taskDeleted = taskList.get(taskNo);
        taskList.remove(taskNo);
        noTasks--;
        return taskDeleted;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getNoTasks() {
        return noTasks;
    }

    public Task getTask(int taskNo) {
        return taskList.get(taskNo);
    }

    public Task getMostRecentTask() { return taskList.get(noTasks - 1); }
}
