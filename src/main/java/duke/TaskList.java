package duke;

import duke.tasks.Task;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public Task remove(int i) {
        Task t = this.tasks.get(i);
        this.tasks.remove(i);
        return t;
    }

    public String listTasks() {
        if (this.tasks.size() == 0) {
            return "There are no tasks currently :)\n" + TextTemplate.LINE_BREAK;
        }
        StringBuilder response = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); ++i) {
            Task t = this.tasks.get(i);
            String taskString = "\n" + String.valueOf(i+1) + ". " + t.toString();
            response.append(taskString);
        }
        response.append("\n");
        response.append(TextTemplate.LINE_BREAK);
        return response.toString();
    }
    public Task markTask(int taskNum) {
        Task t = this.tasks.get(taskNum);
        t.maskAsDone();
        return t;
    }

    public Task unmarkTask(int taskNum) {
        Task t = this.tasks.get(taskNum);
        t.unmark();
        return t;
    }

    private ArrayList<Task> find(String keyword) {
        ArrayList<Task> match = new ArrayList<>();
        for (Task t: this.tasks) {
            if (t.containsKeyword(keyword)) {
                match.add(t);
            }
        }
        return match;
    }
    public String findToString(String keyword) {
        ArrayList<Task> match = this.find(keyword);
        if (match.isEmpty()) {
            return "There are no matching tasks! :(\n" + TextTemplate.LINE_BREAK;
        }
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < match.size(); ++i) {
            Task t = match.get(i);
            String taskString = "\n" + String.valueOf(i+1) + ". " + t.toString();
            response.append(taskString);
        }
        response.append("\n");
        response.append(TextTemplate.LINE_BREAK);
        return response.toString();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Task t: this.tasks) {
            s.append(t.save());
            s.append("\n");
        }
        return s.toString();
    }
}
