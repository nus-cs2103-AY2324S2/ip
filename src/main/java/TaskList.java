import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int taskNum) throws DinoException {
        if (taskNum < 1 || taskNum > taskList.size()) {
            throw new DinoException("Invalid task number. Please provide a valid task number to delete.");
        }

        Task removedTask = taskList.remove(taskNum - 1);

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void listTask() {
        try {
            if (taskList.isEmpty()) {
                throw new DinoException("The list is empty.");
            }
            System.out.println("Here are the tasks that you wanted to do:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                int index = i + 1;
                System.out.println(index + "." + task);
            }
        } catch (DinoException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }
}
