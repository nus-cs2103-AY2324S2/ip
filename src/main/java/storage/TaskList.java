package storage;

import exceptions.ArgumentException;
import tasks.Task;
import parser.Parser;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the todolist of the application
 */
public class TaskList {
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void deleteTask(int index) {
        list.remove(index  -1);
    }

    public void markTaskDone(int index) {
        list.get(index - 1).markDone();
    }

    public void markTaskNotDone(int index) {
        list.get(index - 1).markNotDone();
    }

    public int getSize() {
        return list.size();
    }

    public Task getTask(int index) {
        return list.get(index - 1);
    }

    public String findTasks(String matcher) {
        String output = "";
        int outputIndex = 1;
        for (Task t : list) {
            if (t.isFound(matcher)) {
                output += outputIndex + "." + t.toString() + "\n";
                outputIndex++;
            }
        }
        return output;
    }

    public List<String> toSaveFormat() {
        List<String> output= new ArrayList<>();
        for (Task task : list) {
            output.add(task.toSaveFormat());
        }
        return output;
    }

    public void loadFromSaveFormat(List<String> input) throws ArgumentException {
        for (String line : input) {
            list.add(Parser.parseLine(line));
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (int i=0; i < list.size(); i++) {
            Task t = list.get(i);
            output += (i + 1) + "." + t.toString() + "\n";
        }
        return output;
    }
 }
