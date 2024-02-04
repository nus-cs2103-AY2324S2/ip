package someboty.Managers;

import java.util.ArrayList;

import someboty.Exceptions.InputException;
import someboty.Tasks.Task;

public class taskManager {

    private ArrayList<Task> taskList;
    private fileManager filer;

    // CONSTRUCTOR
    public taskManager(String filePath) {
        this.filer = new fileManager(filePath);
        taskList = this.filer.fetchTasks();
    }

    protected int getListSize() {
        return this.taskList.size();
    }
    
    protected String listTasks() {
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

    protected String setTaskStatus(int index, boolean status) {
        try {
            this.taskList.get(index).setStatus(status);
            return this.taskList.get(index).toString();

         } catch (IndexOutOfBoundsException e) {
            throw new InputException(">>> Bruh, there ain't no task " + String.valueOf(index + 1));
        }
    }

    protected String deleteTask(int index) {
        try {
            Task removedTask = taskList.remove(index);
            return removedTask.toString();

        } catch (IndexOutOfBoundsException e) {
            throw new InputException(">>> Bruh, there ain't no task " + String.valueOf(index + 1));
        }
    }

    protected void clear() {
        this.taskList.clear();
    }

    protected String addTask(char type, String description) {
        Task newTask = Task.createTask(type, description);
        this.taskList.add(newTask);
        return newTask.toString();
    }

    protected void close() {
        this.filer.storeTasks(this.taskList);
    }
    
    
}
