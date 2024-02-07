package duke.task;

import duke.task.Task;

public class TaskList {
    private Task[] tasks;
    private int counter;

    public TaskList() {
        tasks = new Task[100]; // Or any initial size you prefer
        counter = 0;
    }

    public void addTask(Task task) {
        tasks[counter] = task;
        counter++;
    }

    public void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > counter) {
            throw new IllegalArgumentException("Invalid task number");
        }

        for (int i = taskNumber - 1; i < counter - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[counter - 1] = null;
        counter--;
    }

    public void markTaskAsDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > counter) {
            throw new IllegalArgumentException("Invalid task number");
        }

        tasks[taskNumber].markAsDone( );
    }

    public Task[] searchKeyWord(String keyword) {
        int count = 0;
        Task[] containsKeywords = new Task[100];

        for (int i = 0; i < counter; i++) {
            String taskDesc = tasks[i].description;
            if (taskDesc.contains(keyword)) {
                containsKeywords[count] = tasks[i];
                count++;
            }
        }

        return containsKeywords;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public int getCounter() {
        return counter;
    }
}
