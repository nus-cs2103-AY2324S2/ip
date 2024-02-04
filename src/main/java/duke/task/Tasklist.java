package duke.task;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tasklist {
    private List<Task> todolist = new ArrayList<>();

    public Tasklist() {
        restoreData();
    }

    public void addItem(Task item) {
        todolist.add(item);
    }

    public Task removeItem(int index) {
        return todolist.remove(index);
    }

    public boolean isEmpty() {
        return todolist.isEmpty();
    }

    public String printTodolist() {
        StringBuilder s = new StringBuilder();
        int i = 1;
        if (isEmpty()) {
            s = new StringBuilder("Todolist is empty!");
            return s.toString();
        }
        for (Task t : todolist) {
            s.append(i).append(". ").append(t.toString()).append("\n\t");
            i++;
        }
        return s.toString().trim();
    }

    public void markTaskAsDone(int taskNumber, boolean isDone) {
        todolist.get(taskNumber).markAsDone(isDone);
    }

    public List<Task> getTodolist() {
        return todolist;
    }

    public String getTaskString(int taskNumber) {
        return todolist.get(taskNumber).toString();
    }

    public void restoreData() {
        // TODO: Handle details[1]
        try {
            Boolean isDone;
            List<String> tasks = Storage.loadData();
            for (String task : tasks) {
                String[] details = task.split("\\|");
                isDone = details[1] != "0";
                for(int i = 0; i < details.length; i++) {
                     details[i] = details[i].trim();
                }
                if (details[0].equals("T")) {
                    addItem(new Todo(details[2], isDone));
                } else if (details[0].equals("D")) {
                    addItem(new Deadline(details[2], LocalDate.parse(details[3]), isDone));
                } else if (details[0].equals("E")) {
                    addItem(new Event(details[2], LocalDate.parse(details[3]), LocalDate.parse(details[4]), isDone));
                }
            }
        } catch (IOException e) {
            System.out.println("An error has occured while executing loadData()");
        }

    }
}
