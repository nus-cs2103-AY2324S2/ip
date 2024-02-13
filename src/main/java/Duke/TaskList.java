package Duke;

public class TaskList {
    private static Task[] tasks;
    private static int taskNum;

    public TaskList() {
        tasks = new Task[100];
        taskNum = 0;
    }

    public TaskList(Task[] loadTasks) {
        tasks = new Task[100];
        taskNum = 0;

        for (int i = 0; i < loadTasks.length; i++) {
            if (loadTasks[i] != null) {
                tasks[i] = loadTasks[i];
                taskNum++;
            }
        }
    }

    public static int getTaskNum() {
        return taskNum;
    }

    public static Task[] getTasks() {
        return tasks;
    }

    public static Task getTask(int index) {
        return tasks[index];
    }

    public void addTask(Task task) {
        if (taskNum < tasks.length) {
            tasks[taskNum] = task;
            taskNum++;
        }
    }

    public void removeTask(int index) {
        if (index >= 0 && index < taskNum) {
            for (int i = index; i < taskNum - 1; i++) {
                tasks[i] = tasks[i + 1];
            }
            tasks[taskNum - 1] = null;
            taskNum--;
        }
    }

    public void markTaskAsDone(int index) {
        tasks[index].markAsDone();
    }

    public void markTaskAsNotDone(int index) {
        tasks[index].markAsNotDone();
    }
}
