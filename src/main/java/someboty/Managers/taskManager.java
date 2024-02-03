package someboty.Managers;

import java.util.ArrayList;

import someboty.Exceptions.InputException;
import someboty.Tasks.Deadline;
import someboty.Tasks.Event;
import someboty.Tasks.Task;
import someboty.Tasks.ToDo;

public class taskManager {

    private ArrayList<Task> taskList;

    // CONSTRUCTOR
    public taskManager() {
        taskList = fileManager.fetchTasks();
    }

    public String listTasks() {
        if (taskList.size() == 0) {    // special message for empty list
            return "Wow! You have no recorded task! Peek laziness here.";
        }

        String response = "Here are the tasks:\n";

        int index = 0;
        for (Task task : taskList) {
            index++;
            response = response + String.format("%d. %s\n",
                                    index,
                                    task
                                    );
        }

        return response;
    }

    public String setTaskStatus(int index, boolean status) {
        try {
            this.taskList.get(index).setStatus(status);
            return status
                ? "Uppzz lah so hardworking!\n " + this.taskList.get(index).toString()
                : "O...k... as you wish I guess...!\n " + this.taskList.get(index).toString();

         } catch (IndexOutOfBoundsException e) {
            throw new InputException(">>> Bruh, there ain't no task " + String.valueOf(index + 1));
        }
    }

    public String deleteTask(int index) {
        try {
            Task removedTask = taskList.remove(index);

            String response = "Noted. I've removed this task:\n"
                            + String.format("  %s\n", removedTask)
                            + String.format("Now you have %d tasks in the list.", this.taskList.size());
            
            return response;

        } catch (IndexOutOfBoundsException e) {
            throw new InputException(">>> Bruh, there ain't no task " + String.valueOf(index + 1));
        }
    }

    public void clear() {
        this.taskList.clear();
    }

    public String addTask(char type, String description) {
        return type == 'T'
            ? addTodo(description)
            : type == 'D'
            ? addDeadline(description)
            : type == 'E'
            ? addEvent(description)
            : invalidTaskType();
    }

    // add a new task of type "ToDo"
    private String addTodo(String description) {
        ToDo newTodo = new ToDo(description);
        this.taskList.add(newTodo);

        return getTaskMessage(newTodo);
    }

    // add a new task of type "Deadline"
    private String addDeadline(String description) {
        Deadline newDeadline = new Deadline(description);
        this.taskList.add(newDeadline);
        return getTaskMessage(newDeadline);
    }

    // add a new task of type "Event"
    private String addEvent(String description) {
        Event newEvent = new Event(description);
        this.taskList.add(newEvent);
        return getTaskMessage(newEvent);
    }

    public void close() {
        fileManager.storeTasks(this.taskList);
    }

    private static String invalidTaskType() {
        throw new InputException("Unknown task type. Check that your command is correct.");
    }

    // output a message after adding a task to the list.
    private  String getTaskMessage(Task task) {
        return "Got it. I've added this task:\n"
            + String.format("  %s\n", task)
            + String.format("Now you have %d tasks in the list.\n", this.taskList.size())
            +"(Type 'list' to see the full list of tasks)";
    }
}
