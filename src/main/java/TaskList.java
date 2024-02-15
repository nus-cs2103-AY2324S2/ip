import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> listOfTasks;

    TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    TaskList(ArrayList<String> tasks) {
        this.listOfTasks = new ArrayList<>();
        for (String task : tasks) {
            char taskType = task.charAt(1);
            char taskStatus = task.charAt(4);
            boolean isDone;
            if (taskStatus == 'X') {
                isDone = true;
            } else {
                isDone = false;
            }
            String taskDetails = task.substring(7);
            Task addTask;
            switch (taskType) {
                case 'T':
                    addTask = new Todo(taskDetails, isDone);
                    listOfTasks.add(addTask);
                    break;
                case 'D':
                    addTask = new Deadline(taskDetails, isDone);
                    listOfTasks.add(addTask);
                    break;
                case 'E':
                    addTask = new Event(taskDetails, isDone);
                    listOfTasks.add(addTask);
                    break;
                // Add more cases for other subclasses if needed
                default:
                    System.out.println("Unknown task type: " + taskType);
            }
        }
    }

    public void addTask(Task newTask) {
        listOfTasks.add(newTask);
    }

    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    public int getSize() {
        return listOfTasks.size();
    }

    public void removeTask(int index) {
        listOfTasks.remove(index);
    }

    public ArrayList<Task> getList() {
        return listOfTasks;
    }
}
