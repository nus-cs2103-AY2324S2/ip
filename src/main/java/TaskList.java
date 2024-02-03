import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<String> strLst) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < strLst.size(); i++) {
            String line = strLst.get(i);
            String[] lineSplit = line.split("\\|");
            String action = lineSplit[0].trim();
            String description = lineSplit[2].trim();
            String isDone = lineSplit[1].trim();

            if (action.equals("T")) {
                Todo addToDoTask = new Todo(description);
                checkIfDone(addToDoTask, isDone);
                tasks.add(addToDoTask);
            } else if (action.equals("D")) {
                try {
                    Deadline addDeadlineTask = new Deadline(description, lineSplit[3].trim());
                    checkIfDone(addDeadlineTask, isDone);
                    tasks.add(addDeadlineTask);
                } catch (DukeExceptions e) {
                    System.out.println("Corrupted file.");
                }
            } else if (action.equals("E")) {
                String[] splitFromAndTo = lineSplit[3].trim().split("-");
                Event addEventTask = new Event(description, splitFromAndTo[0], splitFromAndTo[1]);
                checkIfDone(addEventTask, isDone);
                tasks.add(addEventTask);
            }
        }

        this.tasks = tasks;
    }

    public static void checkIfDone(Task t, String val) {
        if (val.equals("0")) {
            t.markAsNotDone();
        } else {
            t.markAsDone();
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void showList() {
        try {
            DukeExceptions.checkListNotEmpty(this.tasks);
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }

        Ui.showLine();
        for(int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + "." + t.toString());
        }
        Ui.showLine();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task removeTask(int index) {
        Task toBeRemoved = this.tasks.get(index);
        this.tasks.remove(index);
        return toBeRemoved;
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
