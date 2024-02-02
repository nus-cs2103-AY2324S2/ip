package Oak.task;

import Oak.Utility.FileUtility;
import Oak.task.model.Deadline;
import Oak.task.model.Event;
import Oak.task.model.Task;
import Oak.task.model.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskService {
    private ArrayList<Task> tasks = new ArrayList<>();
    private final String tasklistFilePath = "/src/main/java/Oak.resources/tasklist.txt";
    private final String taskListSep = "\\|";

    private FileUtility fileUtility = new FileUtility();

    public TaskService() {
        this.loadTasks();
    }

    public void loadTasks() {
        ArrayList<String> fileData = new ArrayList<>();

        try {
            fileData = this.fileUtility.loadFile(this.tasklistFilePath);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error reading file.. Unable to load tasks from " + this.tasklistFilePath);
        }

        for (String line : fileData) {
            // TODO: Double check if the formatting for dateTime to string works for parsing
            this.parseTaskList(line);
        }
    }

    private void saveTask(Task task) throws IOException {
        this.fileUtility.writeToFile(this.tasklistFilePath, task.toTaskListStringFormat());
    }

    private void removeTask(Task task) throws IOException {
        this.fileUtility.removeLineFromFile(this.tasklistFilePath, task.toTaskListStringFormat());
    }

    private void parseTaskList(String line) {
        String[] task = line.split(this.taskListSep);
        Task newTask = null;

        if (task.length <= 1) {
            return;
        }

        Boolean isCompleted = task[1].equals("1");

        if (task[0].equals(Todo.typeIcon)) {
            newTask = new Todo(task[2], isCompleted);
        }
        else if (task[0].equals(Deadline.typeIcon)) {
            newTask = new Deadline(task[2], isCompleted, task[3]);
        }
        else if (task[0].equals(Event.typeIcon)) {
            newTask = new Event(task[2], isCompleted, task[3], task[4]);
        }
        else {
            // TODO: Throw invalid task Oak.type exception
            System.out.println("Invalid task detected");
        }

        this.tasks.add(newTask);
    }


    public String addTodo(String taskName) throws IOException {
        Todo newTodo = new Todo(taskName);

        this.tasks.add(newTodo);
        this.saveTask(newTodo);

        return String.format("Added new Todo: %s", taskName);
    }

    public String addDeadline(String taskName, String byDateTime) throws IOException {
        Deadline newDeadline = new Deadline(taskName, byDateTime);

        this.tasks.add(newDeadline);
        this.saveTask(newDeadline);

        return String.format("Added new Deadline: %s with Due Date: %s", taskName, byDateTime);
    }

    public String addEvent(String taskName, String fromDateTime, String toDateTime) throws IOException {
        Event newEvent = new Event(taskName, fromDateTime, toDateTime);

        this.tasks.add(newEvent);
        this.saveTask(newEvent);

        return String.format("Added new Event: %s occurring from %s to %s", taskName, fromDateTime, toDateTime);
    }

    public String deleteTask(int taskId) throws IOException {
        Task removedTask = this.tasks.remove(taskId);
        this.removeTask(removedTask);

        return String.format("Are you giving up? Or is this task no longer needed?\nHmmm.. I've deleted Task %s for you for now.\nBut, I'll be watching you.", taskId);
    }

    public String markTaskCompleted(int taskId) {
        this.tasks.get(taskId).markTaskCompleted();

        return "Ok! I've marked Task " + (taskId + 1) + " as completed!";
    }

    public String markTaskUncompleted(int taskId) {
        // TODO: Exception handling for if task does not exist
        this.tasks.get(taskId).markTaskNotCompleted();

        return "Hmmm, were you teasing me?\n" +
                "Well, I've marked Task " + (taskId + 1) +  " as uncompleted,\n" +
                "But don't do this again, you hear me?";
    }

    public String getAllTasks() {
        StringBuilder returnVal = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            returnVal.append(String.format("%d. %s", i + 1, this.tasks.get(i)));

            // Only add new line if its not the last task
            if (i < this.tasks.size() - 1) {
                returnVal.append("\n");
            }
        }

        return returnVal.toString();
    }
}
