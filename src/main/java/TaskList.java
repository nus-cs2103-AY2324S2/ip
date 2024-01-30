import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    public int getTaskSize() {
        return this.tasks.size();
    }

    public void updateTaskToStorage(fileStorage fs) throws myBotException {
        try {
            fs.updateFile(this.tasks);
        } catch (myBotException e) {
            throw new myBotException(e.getMessage());
        }

    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    public void removeTask(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    public String taskToString(int taskNumber) {
        return this.tasks.get(taskNumber).toString();
    }

    public String taskStatusIcon(int taskNumber) {
        return this.tasks.get(taskNumber).getStatusIcon();
    }

    public void taskMarkAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markAsDone();
    }

    public void taskUncheckTask(int taskNumber) {
        this.tasks.get(taskNumber).uncheckingTask();
    }

    public void listTask() {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            int taskNumber = i + 1;
            System.out.println(taskNumber + "." + tasks.get(i).toString());
        }
    }
}
