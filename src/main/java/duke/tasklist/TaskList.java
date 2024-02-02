package duke.tasklist;

import duke.dukeexception.DukeException;
import duke.dukeexception.DukeTaskNotFound;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> saveFile) {
        list = saveFile;
    }

    public String add(Task newTask) {
        list.add(newTask);
        return "Got it. I've added this duke.task:" + System.lineSeparator() + newTask.taskInfo() + listStatus();
    }

    public String list() {
        if (list.size() == 0) {
            return "The list is empty";
        }
        int index = 1;
        String output = "Here are the tasks in your list" + System.lineSeparator();
        for (Task item : list) {
            output = output + index + ".";
            index++;
            output += item.taskInfo();
        }
        return output;
    }

    public String listStatus() {
        int length = list.size();
        return "Now you have " + length + " task" + (length > 1 ? "s" : "") + " in the list." + System.lineSeparator();
    }

    public String delete(int index) throws DukeException {
        try {
            Task removed = list.remove(index - 1);
            removed.taskInfo();
            return "Noted. I've removed this duke.task:" + System.lineSeparator() + listStatus();
        } catch (IndexOutOfBoundsException e3) {
            throw new DukeTaskNotFound(index);
        }
    }
    public String mark(int index) throws DukeException {
        try {
            Task task = list.get(index - 1);
            return task.mark();
        } catch (IndexOutOfBoundsException e3) {
            throw new DukeTaskNotFound(index);
        }
    }

    public String unmark(int index) throws DukeException {
        try {
            Task task = list.get(index - 1);
            return task.unmark();
        } catch (IndexOutOfBoundsException e3) {
            throw new DukeTaskNotFound(index);
        }
    }

    public String check(LocalDate date) throws DukeException {
        String output = String.format("Tasks on %s:", date) + System.lineSeparator();
        for (Task item : list) {
            output += item.happenOn(date);
        }
            return output;

    }

    public String saveFormat() {
        String output = "";
        for (Task t: list) {
            output += t.saveOutput();
            output += System.lineSeparator();
        }
        return output;
    }
}
