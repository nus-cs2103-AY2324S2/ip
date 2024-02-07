package duke.command;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void printTaskList() {
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public Task markTask(int index) {
        tasks.get(index).updateIsDone(true);
        return tasks.get(index);
    }

    public Task unmarkTask(int index) {
        tasks.get(index).updateIsDone(false);
        return tasks.get(index);
    }

    public Task addTodo(String name) {
        Task task = new ToDo(name);
        tasks.add(task);

        return task;
    }

    public Task addDeadline(String name, String from) {
        Task task = new Deadline(name, from);
        tasks.add(task);

        return task;
    }

    public Task addEvent(String name, String from, String to) {
        Task task = new Event(name, from, to);
        tasks.add(task);

        return task;
    }
    public Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);

        return task;
    }

    public void findMatchingTasks(String match) {
        for (Task t : tasks) {
            if (t.isMatchingDescription(match)) {
                System.out.println(t.toString());
            }
        }
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    public String getTaskString(int index) {
        return tasks.get(index).toString();
    }
}