package duke;
import java.util.ArrayList;

import duke.exceptions.DukeCeption;
import duke.exceptions.IncorrectFormatException;
import duke.exceptions.NumberOutOfBoundsException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class TaskList {
    
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public int getSize() {
        return list.size();
    }

    public void addNewTask(Task task) {
        list.add(task);
    }

    public Task getTask(String number) throws DukeCeption {
        try {
            int taskNumber = Integer.parseInt(number) - 1;
            Task task = list.get(taskNumber);
            return task;
        } catch (NumberFormatException e) {
            throw new IncorrectFormatException("The number given is unrecognizable");
        } catch (IndexOutOfBoundsException e) {
            throw new NumberOutOfBoundsException("The number is not in this list!");
        }
    }

    public Task delete(String number) throws DukeCeption {
            Task task = this.getTask(number);
            int taskNumber = Integer.parseInt(number) - 1;
            list.remove(taskNumber);
            return task;
    }

    public void loadList(ArrayList<String> dataStrings) {
        for (String line : dataStrings) {
            this.textToTask(line);
        }
    }

    public void textToTask(String line) {
        String[] separate = line.split(";;");
        String taskType = separate[0];
        boolean isDone = separate[1].equals("1") ? true : false;
        String description = separate[2];
        Task task;

        switch (taskType) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "D":
                String by = separate[3];
                task = new Deadline(description, by, isDone);
                break;
            default:
                String from = separate[3];
                String to = separate[4];
                task = new Event(description, from, to, isDone);
                break;
        }
        list.add(task);
    }

    public ArrayList<String> saveFormat() {
        ArrayList<String> dataToText = new ArrayList<>();
        for (Task task : list) {
            dataToText.add(task.saveFormat());
        }
        return dataToText;
    }

}
