package service;

public class TaskService {
    private String[] taskList = new String[100]; // According to specifications, there cannot be more than 100 tasks
    private int taskListPointer = 0;

    public void addTask(String taskName) {
        this.taskList[this.taskListPointer++] = taskName;

        System.out.println(String.format("Added: %s", taskName));
    }

    public void getAllTasks() {
        for (int i = 0; i < this.taskList.length; i++) {
            String curTask = this.taskList[i];

            if (curTask == null) {
                break;
            }

            System.out.println(String.format("%d. %s", i + 1, this.taskList[i]));
        }
    }
}
