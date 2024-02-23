package junjie;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;

import junjie.exceptions.InvalidArgumentException;
import junjie.tasks.DeadlineTask;
import junjie.tasks.EventTask;
import junjie.tasks.Task;
import junjie.tasks.TodoTask;

/**
 * Represents a list of tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructs a task list with the given file contents.
     *
     * @param fileContents The contents of the file.
     */
    public TaskList(String fileContents) {
        if (fileContents.isEmpty()) {
            return;
        }
        String[] tasks = fileContents.split("\n");
        for (String task : tasks) {
            String[] taskDetails = task.split(" \\| ", -1);
            createTaskFromDetails(taskDetails);
        }
    }


    private void createTaskFromDetails(String[] taskDetails) {
        assert taskDetails.length >= 4 : "Task details should have minimum 4 parts";
        try {
            String taskType = taskDetails[0];
            boolean isDone = taskDetails[1].equals("1");
            String taskContent = taskDetails[2];

            switch (taskType) {
            case "T": {
                String[] tags = Parser.splitTags(taskDetails[3]);
                Task task = new TodoTask(taskContent, tags);
                task.markDone(isDone);
                this.add(task);
                break;
            }
            case "D": {
                String deadline = taskDetails[3];
                String[] tags = Parser.splitTags(taskDetails[4]);
                Task task = new DeadlineTask(taskContent, deadline, tags);
                task.markDone(isDone);
                this.add(task);
                break;
            }
            case "E": {
                String eventFrom = taskDetails[3];
                String eventTo = taskDetails[4];
                String[] tags = Parser.splitTags(taskDetails[5]);
                Task task = new EventTask(taskContent, eventFrom, eventTo, tags);
                task.markDone(isDone);
                this.add(task);
                break;
            }
            default:
                throw new InvalidArgumentException("Invalid task type in file.");
            }
        } catch (DateTimeException | InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean add(Task task) {
        assert task != null : "Task is null";
        super.add(task);
        Storage.write(this.toFileString());
        return false;
    }

    @Override
    public Task remove(int index) {
        Task taskRemoved = super.remove(index);
        Storage.write(this.toFileString());
        return taskRemoved;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            str.append(String.format("%d. %s", i + 1, this.get(i).toString()));
            if (i != this.size() - 1) {
                str.append("\n");
            }
        }

        return str.toString();
    }

    /**
     * Returns a string representation of the task list that can be written to a file.
     *
     * @return A string representation of the task list that can be written to a file.
     */
    public String toFileString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            str.append(String.format("%s", this.get(i).toFileString()));
            if (i != this.size() - 1) {
                str.append("\n");
            }
        }

        return str.toString();
    }
}
