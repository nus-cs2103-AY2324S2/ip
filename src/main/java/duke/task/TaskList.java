package duke.task;

import java.util.ArrayList;

import duke.commons.exceptions.DukeException;

import java.io.Serializable;

public class TaskList implements Serializable {

    public static final String INDENT = "     ";

    public static final String LINE =  "____________________________________________________________";

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    public String markTaskDone(int idx) throws DukeException {
        if (idx >= 0 && idx < tasks.size()) {
            tasks.get(idx).markDone();
            return tasks.get(idx).toString();
        } else {
            throw new DukeException("Invalid task index: " + idx);
        }
    }

    public String markTaskUndone(int idx) throws DukeException {
        if (idx >= 0 && idx < tasks.size()) {
            tasks.get(idx).markUndone();
            return tasks.get(idx).toString();
        } else {
            throw new DukeException("Invalid task index: " + idx);
        }
    }

    public String deleteTask(int idx) throws DukeException {
        if (idx >= 0 && idx < tasks.size()) {
            String taskDescription = tasks.get(idx).toString();
            tasks.remove(idx);
            return taskDescription;
        } else {
            throw new DukeException("Invalid task index: " + idx);
        }
    }

    public ArrayList<Integer> findTasksByKeywordsMatching(String[] keywords) throws DukeException {
        ArrayList<Integer> matchingTaskIndices = new ArrayList<>();
        for (int i = 0; i < getNumberTasks(); i++) {
            for (String keyword : keywords) {
                if (tasks.get(i).containsKeyword(keyword)) {
                    matchingTaskIndices.add(i);
                    break;
                }
            }
        }
        return matchingTaskIndices;
    }

    public int getNumberTasks() {
        return tasks.size();
    }

    public ArrayList<String> getTaskRepresentationsByIndices(ArrayList<Integer> indices) {
        ArrayList<String> taskRepresentations = new ArrayList<>();
        for (int i : indices) {
            taskRepresentations.add(tasks.get(i).getRepresentation());
        }
        return taskRepresentations;
    }

    public void printTasks() {
        System.out.println(INDENT + LINE);

        if (tasks.size() == 0) {
            System.out.println(INDENT + "no tasks");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     "  + (i+1) + ". " + tasks.get(i));
            }
        }

        System.out.println(INDENT + LINE + "\n");
    }
}
